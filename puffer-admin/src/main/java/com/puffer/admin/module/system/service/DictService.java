package com.puffer.admin.module.system.service;


import com.puffer.admin.common.util.BaseService;
import com.sting.core.project.SRS;

/**
 * 系统字典服务
 *
 * @author WangYongJi
 */
public interface DictService extends BaseService {
    /**
     * 添加
     */
    SRS insert(SRS param);

    /**
     * 删除
     */
    SRS delete(SRS param);

    /**
     * 修改
     */
    SRS update(SRS param);

    /**
     * 查询详情
     */
    SRS info(SRS param);

    /**
     * 查询集合
     */
    SRS list(SRS param);

    /**
     * 分页查询
     */
    SRS page(SRS param);
}
