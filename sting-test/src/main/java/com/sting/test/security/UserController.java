package com.sting.test.security;

import com.sting.security.rbac.annotation.Role;
import com.sting.security.rbac.annotation.RoleExclude;
import com.sting.security.rbac.annotation.Res;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("用户管理")
@Role({"system", "admin_company"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Res("添加用户")
    @RoleExclude("admin_company")
    @RequestMapping("/insert")
    public String insertUser() {
        return "";
    }

    @Res("删除用户")
    @RoleExclude("admin_company")
    @RequestMapping("/delete")
    public String deleteUser() {
        return "";
    }


}
