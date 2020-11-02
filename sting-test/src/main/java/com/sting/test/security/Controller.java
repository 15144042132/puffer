package com.sting.test.security;

import com.sting.security.rbac.resource.ResC;
import com.sting.security.rbac.resource.ResM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ResC(value = "测试")
@RestController
@RequestMapping("/test")
public class Controller {

    @ResM
    @RequestMapping("/eeee")
    public String a1() {
        return "";
    }

    @ResM
    @RequestMapping("/rrrr")
    public String a2() {
        return "";
    }


}
