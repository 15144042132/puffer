package com.sting.test.security;

import com.sting.security.rbac.Res;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("系统管理")
@RestController
@RequestMapping("/system")
public class SystemController {

    @Res("刷新缓存")
    @RequestMapping("/refreshCache")
    public String refreshCache() {
        return "";
    }

}
