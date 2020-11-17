package com.sting.security.rbac.handler;

import com.sting.db.dao.StDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 创建必须存在的数据库表
 */
@Slf4j
@Component
public class ConfigHandler {
    //数据库操作对象
    private final StDao dao;
    //安全框架初始化状态
    private final Object initStatus;

    ConfigHandler(StDao dao) {
        this.dao = dao;
        initStatus = dao.selectObj(" select id from sys_security_config where code='init_status' and value='1' ");
    }


    /**
     * 检查并初始化配置
     */
    public void checkAndInitConfig() {
        if (initStatus == null) {
            //删除所有数据
            dao.delete("delete from sys_security_config where id<1000");
            //执行初始化
            for (String sql : insert_sql) {
                dao.insert(sql);
            }
        }
    }


    //TODO 执行初始化INSERT
    private static final String[] insert_sql = new String[]{
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (1, 'ID<1000为框架保留配置，不可使用', 'zero', 'zero', NULL, 100);\n",
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (2, '系统秘钥(可以考虑定期更换)', 'system_secret', '67c7beb18fafd77f1319739fa683bc5e', NULL, 100);\n",
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (3, '登录时，允许错误输入密码最大次数', 'login_error_input_max_count', '10', NULL, 100);\n",
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (4, '初始化状态（1=已完成，2=项目重启后执行初始化）', 'init_status', '1', NULL, 100);\n",
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (5, 'root账号，项目首次启动后，必须修改', 'root_account', 'root', NULL, 100);\n",
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (6, 'root密码，项目首次启动后，必须修改', 'root_password', '????????', NULL, 100);\n",
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (7, '请求访问来源控制', 'access_control_allow_origin', '*', NULL, 100);\n",
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (8, '请求头控制', 'access_control_allow_headers', 'authorization,Authorization,Token,token,Auth,DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type', NULL, 100);\n",
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (9, '请求允许方法', 'access_control_allow_methods', 'POST,GET,OPTIONS,DELETE', NULL, 100);\n",
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (10, '请求最大连接时长', 'access_control_max_age', '3600', NULL, 100);\n",
            "INSERT INTO `sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (12, '请求的响应是否允许暴露给页面', 'access_control_allow_credentials', 'true', NULL, 100);\n",
    };

}
