package com.puffer.admin.module.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.puffer.admin.common.mybatis.entity.SysRole;
import com.puffer.admin.module.system.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    private StDao dao;

    @Override
    public SRS insert(SRS param) {
        SysRole SysRole = JSON.parseObject(param.toJSONString(), SysRole.class);
        dao.insert(SysRole);
        return SRS.bySuccess();
    }

    @Override
    public SRS delete(SRS param) {
        List<String> ids = param.getJSONArray("ids").toJavaList(String.class);
        dao.deleteByIds(SysRole.class, ids);
        return SRS.bySuccess();
    }

    @Override
    public SRS update(SRS param) {
        SysRole SysRole = JSON.parseObject(param.toJSONString(), SysRole.class);
        dao.updateById(SysRole);
        return SRS.bySuccess();
    }

    @Override
    public SRS info(SRS param) {
        String id = param.getString("id");
        SysRole SysRole = dao.selectOne(SysRole.class, id);
        return SRS.bySuccess(SysRole);
    }

    @Override
    public SRS list(SRS param) {
        StWrapper<SysRole> wrapper = new StWrapper<>(SysRole.class);
        wrapper.eq("1", 1);
        wrapper.like(StringUtils.isNoneBlank(param.getString("id")), SysRole.ID, param.get("id"));
        List<SysRole> list = dao.list(wrapper);
        return SRS.bySuccess(list);
    }

    @Override
    public SRS page(SRS param) {
        StWrapper<SysRole> wrapper = new StWrapper<>(SysRole.class);
        wrapper.eq("1", 1);
        wrapper.orderByAsc("sort", "path");
        wrapper.like(StringUtils.isNoneBlank(param.getString("id")), SysRole.ID, param.get("id"));
        StPage<SysRole> page = dao.page(new StPage<>(param), wrapper);
        return SRS.bySuccess(page);
    }

}
