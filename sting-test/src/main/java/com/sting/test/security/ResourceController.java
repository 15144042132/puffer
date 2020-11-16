package com.sting.test.security;

import com.sting.security.rbac.annotation.Res;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("资源管理")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Res("读取资源")
    @RequestMapping("/read")
    public String read() {
        return "";
    }

}
