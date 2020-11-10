package com.sting.security.rbac;

import com.sting.security.rbac.authority.RoleHandler;
import com.sting.security.rbac.resource.ResHandler;
import com.sting.security.rbac.table.TableHandler;
import org.springframework.stereotype.Component;

@Component
public class RBACInitialize {

    RBACInitialize(TableHandler tableHandler, RoleHandler roleHandler, ResHandler resHandler) {
        //创建数据库表结构
        tableHandler.createTable();

        //扫描资源，更新数据库
        resHandler.scanResource();
        resHandler.refreshDbResource();

        //扫描角色资源，更新数据库
        roleHandler.scanRole();
    }
}
