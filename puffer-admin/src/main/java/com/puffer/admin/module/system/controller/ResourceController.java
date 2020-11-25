package com.puffer.admin.module.system.controller;


import com.puffer.admin.module.system.service.DictService;
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
    private DictService dictService;

    @Res("添加字典")
    @RequestMapping("/insert")
    public SRS insert(@RequestBody SRS param) {
        return dictService.insert(param);
    }

    @Res("删除字典")
    @RequestMapping("/delete")
    public SRS delete(@RequestBody SRS param) {
        return dictService.delete(param);
    }

    @Res("修改字典")
    @RequestMapping("/update")
    public SRS update(@RequestBody SRS param) {
        return dictService.update(param);
    }

    @Res("字典详情查询")
    @RequestMapping("/info")
    public SRS info(@RequestBody SRS param) {
        return dictService.info(param);
    }

    @Res("字典列表查询")
    @RequestMapping("/list")
    public SRS list(@RequestBody SRS param) {
        return dictService.list(param);
    }

    @Res("字典分页查询")
    @RequestMapping("/page")
    public SRS page(@RequestBody SRS param) {
        return dictService.page(param);
    }
}