package com.sting.test.security;

import com.sting.security.rbac.annotation.Res;
import com.sting.security.rbac.annotation.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("资源管理")
@Role("admin_company")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Res("读取资源")
    @RequestMapping("/read")
    public String read() {
        return "";
    }

}
