package com.sting.test.security;

import com.sting.security.rbac.Res;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

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


}
