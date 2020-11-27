package com.sting.security.rbac;

import com.sting.security.rbac.handler.ConfigHandler;
import com.sting.security.rbac.handler.ResHandler;
import com.sting.security.rbac.handler.RootRoleHandler;
import com.sting.security.rbac.handler.TableHandler;

/**
 * 执行项目初始化
 */
public class Initialization {

    public Initialization(TableHandler tableHandler, ConfigHandler configHandler, ResHandler resHandler, RootRoleHandler accountHandler) {
        //建表
        tableHandler.checkAndCreateTable();

        //初始化配置
        configHandler.checkAndInitConfig();

        //扫描资源
        resHandler.scanResource();

        //更新资源
        resHandler.refreshDbResource();

        //创建ROOT角色
        accountHandler.checkAndCreateRoot();
    }

}
