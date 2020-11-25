package com.sting.test.security;

import com.sting.security.rbac.Res;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Res("开放路径")
    @RequestMapping("/public/test")
    public String publicTest() {
        return "publicTest";
    }

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
