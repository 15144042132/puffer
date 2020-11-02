package com.sting.test.security;

import com.sting.security.rbac.resource.Res;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res( "测试A")
@RestController
@RequestMapping("/testA")
public class Controller1 {

    @Res("a1")
    @RequestMapping("/a1")
    public String a1() {
        return "";
    }

    @Res("a2")
    @RequestMapping("/a2")
    public String a2() {
        return "";
    }


}
