package com.puffer.admin.module.system.controller;


import com.puffer.admin.module.system.service.RoleService;
import com.sting.core.project.SRS;
import com.sting.security.rbac.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("角色管理")
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Res("添加觉散热")
    @RequestMapping("/insert")
    public SRS insert(@RequestBody SRS param) {
        return roleService.insert(param);
    }

    @Res("删除角色")
    @RequestMapping("/delete")
    public SRS delete(@RequestBody SRS param) {
        return roleService.delete(param);
    }

    @Res("修改角色")
    @RequestMapping("/update")
    public SRS update(@RequestBody SRS param) {
        return roleService.update(param);
    }

    @Res("角色详情查询")
    @RequestMapping("/info")
    public SRS info(@RequestBody SRS param) {
        return roleService.info(param);
    }

    @Res("角色列表查询")
    @RequestMapping("/list")
    public SRS list(@RequestBody SRS param) {
        return roleService.list(param);
    }

    @Res("角色分页查询")
    @RequestMapping("/page")
    public SRS page(@RequestBody SRS param) {
        return roleService.page(param);
    }
}