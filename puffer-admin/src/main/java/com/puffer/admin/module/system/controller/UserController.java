package com.puffer.admin.module.system.controller;


import com.puffer.admin.module.system.service.UserService;
import com.sting.core.project.SRS;
import com.sting.security.rbac.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("用户管理")
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Res("添加root账户")
    @RequestMapping("/public/insertRoot")
    public SRS insertRoot(@RequestBody SRS param) {
        return userService.insertRoot(param);
    }

    @Res("添加用户")
    @RequestMapping("/insert")
    public SRS insert(@RequestBody SRS param) {
        return userService.insert(param);
    }

    @Res("删除用户")
    @RequestMapping("/delete")
    public SRS delete(@RequestBody SRS param) {
        return userService.delete(param);
    }

    @Res("修改用户")
    @RequestMapping("/update")
    public SRS update(@RequestBody SRS param) {
        return userService.update(param);
    }

    @Res("用户详情查询")
    @RequestMapping("/info")
    public SRS info(@RequestBody SRS param) {
        return userService.info(param);
    }

    @Res("用户列表查询")
    @RequestMapping("/list")
    public SRS list(@RequestBody SRS param) {
        return userService.list(param);
    }

    @Res("字用户分页查询")
    @RequestMapping("/page")
    public SRS page(@RequestBody SRS param) {
        return userService.page(param);
    }

    @Res("用户登录")
    @RequestMapping("/public/login")
    public SRS login(@RequestBody SRS param) {
        return userService.login(param);
    }

    @Res("退出登录")
    @RequestMapping("/public/logout")
    public SRS logout(@RequestBody SRS param) {
        return userService.logout(param);
    }

    @Res("锁定用户")
    @RequestMapping("/lockUser")
    public SRS lockUser(@RequestBody SRS param) {
        return userService.lockUser(param);
    }

}