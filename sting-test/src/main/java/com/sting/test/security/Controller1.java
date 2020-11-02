package com.sting.test.security;

import com.sting.security.rbac.resource.ResC;
import com.sting.security.rbac.resource.ResM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ResC(value = "测试A")
@RestController
@RequestMapping("/testA")
public class Controller1 {

    @ResM
    @RequestMapping("/a1")
    public String a1() {
        return "";
    }

    @ResM
    @RequestMapping("/a2")
    public String a2() {
        return "";
    }


}
