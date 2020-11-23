package com.sting.security.rbac.handler;

import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper;
import com.sting.security.rbac.entity.StLinkRoleResource;
import com.sting.security.rbac.entity.StResource;
import com.sting.security.rbac.entity.StRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建系统管理员
 */
@Slf4j
@Component
public class RootRoleHandler {
    @Autowired
    private StDao dao;

    /**
     * 检查并创建ROOT角色
     */
    public void checkAndCreateRoot() {
        StRole stRole = dao.selectOne(new StWrapper<>(StRole.class).eq("code", "root"));
        if (stRole == null) {
            stRole = new StRole();
            stRole.setName("ROOT");
            stRole.setCode("root");
            dao.insert(stRole);
        }

        ArrayList<StLinkRoleResource> linkRoleResources = new ArrayList<>();
        List<StResource> allResources = dao.list(StResource.class);
        for (StResource resource : allResources) {
            StLinkRoleResource roleResource = new StLinkRoleResource();
            roleResource.setResourceId(resource.getId());
            roleResource.setRoleId(stRole.getId());
            linkRoleResources.add(roleResource);
        }
        dao.delete(" delete from sys_link_role_resource where role_id = " + stRole.getId());
        dao.insertBatch(linkRoleResources);
    }
}
