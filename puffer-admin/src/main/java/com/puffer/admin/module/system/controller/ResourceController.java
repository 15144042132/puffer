package com.puffer.admin.module.system.controller;


import com.puffer.admin.module.system.service.ResourceService;
import com.sting.core.project.SRS;
import com.sting.security.rbac.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Res("资源管理")
@RestController
@RequestMapping("/system/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @Res("添加资源")
    @RequestMapping("/insert")
    public SRS insert(@RequestBody SRS param) {
        return resourceService.insert(param);
    }

    @Res("删除资源")
    @RequestMapping("/delete")
    public SRS delete(@RequestBody SRS param) {
        return resourceService.delete(param);
    }

    @Res("修改资源")
    @RequestMapping("/update")
    public SRS update(@RequestBody SRS param) {
        return resourceService.update(param);
    }

    @Res("资源详情查询")
    @RequestMapping("/info")
    public SRS info(@RequestBody SRS param) {
        return resourceService.info(param);
    }

    @Res("资源列表查询")
    @RequestMapping("/list")
    public SRS list(@RequestBody SRS param) {
        return resourceService.list(param);
    }

    @Res("资源分页查询")
    @RequestMapping("/page")
    public SRS page(@RequestBody SRS param) {
        return resourceService.page(param);
    }
}