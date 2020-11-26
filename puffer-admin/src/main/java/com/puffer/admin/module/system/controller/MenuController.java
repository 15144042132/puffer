package com.puffer.admin.module.system.controller;


import com.puffer.admin.module.system.service.MenuService;
import com.sting.core.project.SRS;
import com.sting.security.rbac.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Res("添加菜单")
    @RequestMapping("/insert")
    public SRS insert(@RequestBody SRS param) {
        return menuService.insert(param);
    }

    @Res("删除菜单")
    @RequestMapping("/delete")
    public SRS delete(@RequestBody SRS param) {
        return menuService.delete(param);
    }

    @Res("修改菜单")
    @RequestMapping("/update")
    public SRS update(@RequestBody SRS param) {
        return menuService.update(param);
    }

    @Res("菜单详情")
    @RequestMapping("/info")
    public SRS info(@RequestBody SRS param) {
        return menuService.info(param);
    }

    @Res("菜单列表")
    @RequestMapping("/list")
    public SRS list(@RequestBody SRS param) {
        return menuService.list(param);
    }

    @Res("菜单分页查询")
    @RequestMapping("/page")
    public SRS page(@RequestBody SRS param) {
        return menuService.page(param);
    }
}