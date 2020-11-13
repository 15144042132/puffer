package com.sting.test.security;

import com.sting.security.rbac.annotation.Res;
import com.sting.security.rbac.annotation.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("角色管理")
@Role("admin_company")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Res("添加角色")
    @RequestMapping("/insert")
    public String insert() {
        return "";
    }

    @Res("删除角色")
    @RequestMapping("/delete")
    public String delete() {
        return "";
    }

}
