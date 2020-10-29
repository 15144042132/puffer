package com.sting.test.security;

import com.sting.security.rbac.ResC;
import com.sting.security.rbac.ResM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ResC
@RestController
@RequestMapping("/aaa")
public class Controller1 {

    @ResM
    @RequestMapping("/aaa")
    public String aaaaaa() {
        return "";
    }
}
