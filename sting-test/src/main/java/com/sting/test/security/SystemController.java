package com.sting.test.security;

import com.sting.security.rbac.annotation.Res;
import com.sting.security.rbac.annotation.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("系统管理")
@Role("system")
@RestController
@RequestMapping("/system")
public class SystemController {

    @Res("刷新缓存")
    @RequestMapping("/refreshCache")
    public String refreshCache() {
        return "";
    }

}
