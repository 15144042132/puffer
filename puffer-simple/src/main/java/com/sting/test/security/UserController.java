package com.sting.test.security;

import com.sting.security.rbac.Res;
import com.sting.security.rbac.config.SecurityConfig;
import com.sting.security.rbac.entity.StSysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Lazy
    SecurityConfig securityConfig;

    @Res("添加用户")
    @RequestMapping("/insert")
    public String insertUser() {
        return "123";
    }

    @Res("删除用户")
    @RequestMapping("/delete")
    public String deleteUser() {
        return "123";
    }

    @Res("修改安全配置")
    @RequestMapping("/public/update")
    public StSysConfig update() {
        return securityConfig.loginErrorInputMaxCount("999");
    }

    @Res("查询")
    @RequestMapping("/public/select")
    public StSysConfig select() {
        return securityConfig.loginErrorInputMaxCount();
    }

}
