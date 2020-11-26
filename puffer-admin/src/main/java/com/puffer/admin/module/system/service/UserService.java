package com.puffer.admin.module.system.service;


import com.puffer.admin.common.util.BaseService;
import com.sting.core.project.SRS;

/**
 * 用户服务
 *
 * @author WangYongJi
 */
public interface UserService extends BaseService {
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

    /**
     * 登录
     */
    SRS login(SRS param);

    /**
     * 登出
     */
    SRS logout(SRS param);

    /**
     * 锁定用户
     */
    SRS lockUser(SRS param);

    /**
     * 添加ROOT账户
     */
    SRS insertRoot(SRS param);
}
