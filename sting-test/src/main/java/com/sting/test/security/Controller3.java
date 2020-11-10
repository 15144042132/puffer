package com.sting.test.security;

import com.sting.security.rbac.annotation.Role;
import com.sting.security.rbac.annotation.RoleExclude;
import com.sting.security.rbac.annotation.Res;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("测试C")
@Role({"system", "admin_company"})
@RestController
@RequestMapping("/test")
public class Controller3 {

    @Res("eeee")
    @RoleExclude("admin_company")
    @RequestMapping("/eeee")
    public String a1() {
        return "";
    }

    @Res("rrrr")
    @RoleExclude("admin_company")
    @RequestMapping("/rrrr")
    public String a2() {
        return "";
    }


}
