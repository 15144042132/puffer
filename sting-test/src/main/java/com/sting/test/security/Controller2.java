package com.sting.test.security;

import com.sting.security.rbac.resource.ResC;
import com.sting.security.rbac.resource.ResM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ResC
@RestController
@RequestMapping("/testB")
public class Controller2 {

    @ResM
    @RequestMapping("/b1")
    public String b1() {
        return "";
    }

    @ResM
    @RequestMapping("/b2")
    public String b2() {
        return "";
    }


}
