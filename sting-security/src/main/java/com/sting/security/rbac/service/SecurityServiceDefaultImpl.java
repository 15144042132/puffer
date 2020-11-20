package com.sting.security.rbac.service;

import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper;
import com.sting.security.rbac.entity.StLinkRoleResource;
import com.sting.security.rbac.entity.StLinkRoleUser;
import com.sting.security.rbac.entity.StResource;
import com.sting.security.rbac.entity.StRole;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityServiceDefaultImpl implements SecurityService {
    @Autowired
    private StDao stDao;

    @Override
    public List<String> userResourceUrls(String userId) {
        return userResources(userId).stream().map(StResource::getUrl).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> userRoleIds(String userId) {
        return userRoles(userId).stream().map(StRole::getId).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> userRoleCodes(String userId) {
        return userRoles(userId).stream().map(StRole::getCode).distinct().collect(Collectors.toList());
    }

    @Override
    public List<StResource> userResources(String userId) {
        StWrapper<StLinkRoleResource> wrapper =
                new StWrapper<>(StLinkRoleResource.class)
                        .select("resource_id")
                        .in("role_id", userRoleIds(userId));
        List<Object> resourceIds = stDao.listObj(wrapper);
        return stDao.list(new StWrapper<>(StResource.class).in("id", resourceIds));
    }

    @Override
    public List<StRole> userRoles(String userId) {
        StWrapper<StLinkRoleUser> wrapper =
                new StWrapper<>(StLinkRoleUser.class)
                        .select("role_id")
                        .in("userId", userId);
        List<Object> roleIds = stDao.listObj(wrapper);
        return stDao.list(new StWrapper<>(StRole.class).in("id", roleIds));
    }
}
