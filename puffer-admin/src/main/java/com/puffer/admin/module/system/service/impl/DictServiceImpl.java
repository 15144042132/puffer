package com.puffer.admin.module.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.puffer.admin.common.mybatis.entity.SysDict;
import com.puffer.admin.module.system.service.DictService;
import com.sting.core.project.SRS;
import com.sting.db.dao.StDao;
import com.sting.db.entity.StPage;
import com.sting.db.wrapper.StWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典表 服务实现类
 */
@CacheConfig(cacheNames = "dict:code")
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private StDao dao;

    @Override
    public SRS insert(SRS param) {
        SysDict sysDict = JSON.parseObject(param.toJSONString(), SysDict.class);
        dao.insert(sysDict);
        return SRS.bySuccess();
    }

    @Override
    public SRS delete(SRS param) {
        List<String> ids = param.getJSONArray("ids").toJavaList(String.class);
        dao.deleteByIds(SysDict.class, ids);
        return SRS.bySuccess();
    }

    @Override
    public SRS update(SRS param) {
        SysDict sysDict = JSON.parseObject(param.toJSONString(), SysDict.class);
        dao.updateById(sysDict);
        return SRS.bySuccess();
    }

    @Override
    public SRS info(SRS param) {
        String id = param.getString("id");
        SysDict sysDict = dao.selectOne(SysDict.class, id);
        return SRS.bySuccess(sysDict);
    }

    @Override
    public SRS list(SRS param) {
        StWrapper<SysDict> wrapper = new StWrapper<>(SysDict.class);
        wrapper.eq("1", 1);
        wrapper.like(StringUtils.isNoneBlank(param.getString("id")), SysDict.ID, param.get("id"));
        List<SysDict> list = dao.list(wrapper);
        return SRS.bySuccess(list);
    }

    @Override
    public SRS page(SRS param) {
        StWrapper<SysDict> wrapper = new StWrapper<>(SysDict.class);
        wrapper.eq("1", 1);
        wrapper.orderByAsc("sort", "path");
        wrapper.like(StringUtils.isNoneBlank(param.getString("id")), SysDict.ID, param.get("id"));
        StPage<SysDict> page = dao.page(new StPage<>(param), wrapper);
        return SRS.bySuccess(page);
    }


    @Override
    @Cacheable(key = "#code")
    public String getValue(String code) {
        SysDict sysDict = dao.selectOne(new StWrapper<>(SysDict.class).eq("code", code));
        if (sysDict == null) {
            return null;
        }
        return sysDict.getValue();
    }

    @Override
    @Cacheable(key = "#code")
    public String getValue(String code, String defaultValue) {
        SysDict sysDict = dao.selectOne(new StWrapper<>(SysDict.class).eq("code", code));
        if (sysDict == null) {
            return defaultValue;
        }
        return sysDict.getValue();
    }

    @Override
    @CachePut(key = "#code")
    public String setValue(String code, String value) {
        SysDict sysDict = new SysDict();
        sysDict.setValue(value);
        dao.update(sysDict, new StWrapper<>(SysDict.class).eq("code", code));
        return code;
    }
}
