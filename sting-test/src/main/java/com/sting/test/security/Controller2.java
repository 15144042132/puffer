package com.sting.test.security;

import com.sting.security.rbac.authority.Role;
import com.sting.security.rbac.resource.Res;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("测试B")
@Role({"admin_company"})
@RestController
@RequestMapping("/testB")
public class Controller2 {

    @Res("Controller2")
    @RequestMapping("/b1")
    public String b1() {
        return "";
    }

    @Res("Controller2")
    @RequestMapping("/b2")
    public String b2() {
        return "";
    }

}
