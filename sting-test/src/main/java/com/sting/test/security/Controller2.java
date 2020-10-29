package com.sting.test.security;

import com.sting.security.rbac.ResC;
import com.sting.security.rbac.ResM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ResC
@RestController
@RequestMapping("/bbb")
public class Controller2 {

    @ResM
    @RequestMapping("/bbbb")
    public String bbbbb() {
        return "";
    }
}
