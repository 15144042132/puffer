package com.puffer.admin.module.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.puffer.admin.common.mybatis.entity.SysMenu;
import com.puffer.admin.module.system.service.MenuService;
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
public class MenuServiceImpl implements MenuService {

    @Autowired
    private StDao dao;

    @Override
    public SRS insert(SRS param) {
        SysMenu SysMenu = JSON.parseObject(param.toJSONString(), SysMenu.class);
        dao.insert(SysMenu);
        return SRS.bySuccess();
    }

    @Override
    public SRS delete(SRS param) {
        List<String> ids = param.getJSONArray("ids").toJavaList(String.class);
        dao.deleteByIds(SysMenu.class, ids);
        return SRS.bySuccess();
    }

    @Override
    public SRS update(SRS param) {
        SysMenu SysMenu = JSON.parseObject(param.toJSONString(), SysMenu.class);
        dao.updateById(SysMenu);
        return SRS.bySuccess();
    }

    @Override
    public SRS info(SRS param) {
        String id = param.getString("id");
        SysMenu SysMenu = dao.selectOne(SysMenu.class, id);
        return SRS.bySuccess(SysMenu);
    }

    @Override
    public SRS list(SRS param) {
        StWrapper<SysMenu> wrapper = new StWrapper<>(SysMenu.class);
        wrapper.eq("1", 1);
        wrapper.like(StringUtils.isNoneBlank(param.getString("id")), SysMenu.ID, param.get("id"));
        List<SysMenu> list = dao.list(wrapper);
        return SRS.bySuccess(list);
    }

    @Override
    public SRS page(SRS param) {
        StWrapper<SysMenu> wrapper = new StWrapper<>(SysMenu.class);
        wrapper.eq("1", 1);
        wrapper.orderByAsc("sort", "path");
        wrapper.like(StringUtils.isNoneBlank(param.getString("id")), SysMenu.ID, param.get("id"));
        StPage<SysMenu> page = dao.page(new StPage<>(param), wrapper);
        return SRS.bySuccess(page);
    }
    
}
