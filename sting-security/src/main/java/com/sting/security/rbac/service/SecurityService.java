package com.sting.security.rbac.service;

import com.sting.security.rbac.entity.StResource;
import com.sting.security.rbac.entity.StRole;

import java.util.List;

public interface SecurityService {
    /**
     * 查询用户的资源
     */
    List<String> userResourceUrls(String userId);

    /**
     * 查询用户角色
     */
    List<String> userRoleIds(String userId);
    /**
     * 查询用户角色
     */
    List<String> userRoleCodes(String userId);

    /**
     * 查询用户的资源
     */
    List<StResource> userResources(String userId);

    /**
     * 查询用户角色
     */
    List<StRole> userRoles(String userId);

}
