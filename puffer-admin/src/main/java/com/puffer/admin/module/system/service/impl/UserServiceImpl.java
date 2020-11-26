package com.puffer.admin.module.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.puffer.admin.common.mybatis.entity.SysUser;
import com.puffer.admin.module.system.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private StDao dao;

    @Override
    public SRS insert(SRS param) {
        SysUser SysUser = JSON.parseObject(param.toJSONString(), SysUser.class);
        dao.insert(SysUser);
        return SRS.bySuccess();
    }

    @Override
    public SRS delete(SRS param) {
        List<String> ids = param.getJSONArray("ids").toJavaList(String.class);
        dao.deleteByIds(SysUser.class, ids);
        return SRS.bySuccess();
    }

    @Override
    public SRS update(SRS param) {
        SysUser SysUser = JSON.parseObject(param.toJSONString(), SysUser.class);
        dao.updateById(SysUser);
        return SRS.bySuccess();
    }

    @Override
    public SRS info(SRS param) {
        String id = param.getString("id");
        SysUser SysUser = dao.selectOne(SysUser.class, id);
        return SRS.bySuccess(SysUser);
    }

    @Override
    public SRS list(SRS param) {
        StWrapper<SysUser> wrapper = new StWrapper<>(SysUser.class);
        wrapper.eq("1", 1);
        wrapper.like(StringUtils.isNoneBlank(param.getString("id")), SysUser.ID, param.get("id"));
        List<SysUser> list = dao.list(wrapper);
        return SRS.bySuccess(list);
    }

    @Override
    public SRS page(SRS param) {
        StWrapper<SysUser> wrapper = new StWrapper<>(SysUser.class);
        wrapper.eq("1", 1);
        wrapper.orderByAsc("sort", "path");
        wrapper.like(StringUtils.isNoneBlank(param.getString("id")), SysUser.ID, param.get("id"));
        StPage<SysUser> page = dao.page(new StPage<>(param), wrapper);
        return SRS.bySuccess(page);
    }

    @Override
    public SRS login(SRS param) {
        return null;
    }

    @Override
    public SRS logout(SRS param) {
        return null;
    }

    @Override
    public SRS lockUser(SRS param) {
        return null;
    }

    @Override
    public SRS insertRoot(SRS param) {
        String account = param.getString("account");
        String password = param.getString("password");
        //复杂（同时包含数字，字母，特殊符号）
        String regex = "^^" +
                "(?![a-zA-z]+$)" +
                "(?!\\d+$)" +
                "(?![!@#$%^&*_-]+$)" +
                "(?![a-zA-z\\d]+$)" +
                "(?![a-zA-z!@#$%^&*_-]+$)" +
                "(?![\\d!@#$%^&*_-]+$)" +
                "[a-zA-Z\\d!@#$%^&*_-]" +
                "{6,20}+$";
        boolean accountCheck = account.matches(regex);
        boolean passwordCheck = password.matches(regex);

        if (!accountCheck) {
            return SRS.byError("账号复杂过低，要求：同时包含数字，字母，特殊符号");
        }

        if (!passwordCheck) {
            return SRS.byError("密码复杂过低，要求：同时包含数字，字母，特殊符号");
        }

        return SRS.bySuccess();
    }

}