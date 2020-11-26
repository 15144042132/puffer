package com.puffer.admin.module.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.puffer.admin.common.mybatis.entity.SysResource;
import com.puffer.admin.module.system.service.ResourceService;
import com.sting.core.project.SRS;
import com.sting.db.dao.StDao;
import com.sting.db.entity.StPage;
import com.sting.db.wrapper.StWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典表 服务实现类
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private StDao dao;

    @Override
    public SRS insert(SRS param) {
        SysResource SysResource = JSON.parseObject(param.toJSONString(), SysResource.class);
        dao.insert(SysResource);
        return SRS.bySuccess();
    }

    @Override
    public SRS delete(SRS param) {
        List<String> ids = param.getJSONArray("ids").toJavaList(String.class);
        dao.deleteByIds(SysResource.class, ids);
        return SRS.bySuccess();
    }

    @Override
    public SRS update(SRS param) {
        SysResource SysResource = JSON.parseObject(param.toJSONString(), SysResource.class);
        dao.updateById(SysResource);
        return SRS.bySuccess();
    }

    @Override
    public SRS info(SRS param) {
        String id = param.getString("id");
        SysResource SysResource = dao.selectOne(SysResource.class, id);
        return SRS.bySuccess(SysResource);
    }

    @Override
    public SRS list(SRS param) {
        StWrapper<SysResource> wrapper = new StWrapper<>(SysResource.class);
        wrapper.eq("1", 1);
        wrapper.like(StringUtils.isNoneBlank(param.getString("id")), SysResource.ID, param.get("id"));
        List<SysResource> list = dao.list(wrapper);
        return SRS.bySuccess(list);
    }

    @Override
    public SRS page(SRS param) {
        StWrapper<SysResource> wrapper = new StWrapper<>(SysResource.class);
        wrapper.eq("1", 1);
        wrapper.orderByAsc("sort", "path");
        wrapper.like(StringUtils.isNoneBlank(param.getString("id")), SysResource.ID, param.get("id"));
        StPage<SysResource> page = dao.page(new StPage<>(param), wrapper);
        return SRS.bySuccess(page);
    }

}
