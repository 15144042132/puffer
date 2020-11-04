package com.sting.test.security;

import com.sting.security.rbac.authority.Role;
import com.sting.security.rbac.resource.Res;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("测试A")
@Role("system")
@RestController
@RequestMapping("/testA")
public class Controller1 {

    @Res("a111")
    @RequestMapping("/a1")
    public String a1() {
        return "";
    }

//    @Res("a222")
//    @RequestMapping("/a2")
//    public String a2() {
//        return "";
//    }

//    @Res("a000")
//    @RequestMapping("/a2")
//    public String a2() {
//        return "";
//    }


    @Res("a999")
    @RequestMapping("/a99")
    public String a2() {
        return "";
    }


}
