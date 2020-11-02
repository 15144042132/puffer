package com.sting.test.security;

import com.sting.security.rbac.resource.Res;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("测试")
@RestController
@RequestMapping("/test")
public class Controller {

    @Res("eeee")
    @RequestMapping("/eeee")
    public String a1() {
        return "";
    }

    @Res("rrrr")
    @RequestMapping("/rrrr")
    public String a2() {
        return "";
    }


}
