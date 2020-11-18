###权限管理系统基础
~~~
拦截器执行顺序
先添加的先执行，
return true会执行下一个
return false；则直接退出
pre执行循序   1 2 3
post执行循序  3 2 1
after执行循序 3 2 1
~~~

~~~
1.基础表 (src/resource/sql/security.sql)
    角色表 sys_role，必备字段 id、name、role_key
                          
    用户表 sys_user，必备字段 id、account、password、phone、email、status

    资源表 sys_resource，必备字段 id、name、url、details

    角色<n-n>用户 关联表，必备字段 role_id、user_id

    角色<n-n>资源 关联表，必备字段 role_id、resource_id


~~~


### 启动流程

* 1.检查并建表
~~~
角色表 sys_role，必备字段 id、name、role_key
用户表 sys_user，必备字段 id、account、password、phone、email、status
资源表 sys_resource，必备字段 id、name、url、details
关联表 角色n<->n用户，必备字段 role_id、user_id
关联表 角色<n-n>资源，必备字段 role_id、resource_id


INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (1, 'ID<1000为框架保留配置，不可使用', 'zero', 'zero', NULL, 100);
INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (2, '系统秘钥(可以考虑定期更换)', 'system_secret', '67c7beb18fafd77f1319739fa683bc5e', NULL, 100);
INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (3, '登录时，允许错误输入密码最大次数', 'login_error_input_max_count', '10', NULL, 100);
INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (4, '初始化状态（1=已完成，2=项目重启后执行初始化）', 'init_status', '1', NULL, 100);
INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (5, 'root账号，项目首次启动后，必须修改', 'root_account', 'root', NULL, 100);
INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (6, 'root密码，项目首次启动后，必须修改', 'root_password', '????????', NULL, 100);
INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (7, '请求访问来源控制', 'access_control_allow_origin', '*', NULL, 100);
INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (8, '请求头控制', 'access_control_allow_headers', 'authorization,Authorization,Token,token,Auth,DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type', NULL, 100);
INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (9, '请求允许方法', 'access_control_allow_methods', 'POST,GET,OPTIONS,DELETE', NULL, 100);
INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (10, '请求最大连接时长', 'access_control_max_age', '3600', NULL, 100);
INSERT INTO `puffer`.`sys_security_config`(`id`, `name`, `code`, `value`, `des`, `sort`) VALUES (12, '请求的响应是否允许暴露给页面', 'access_control_allow_credentials', 'true', NULL, 100);

~~~
* 1.检查并初始化配置
~~~
项目首次启动，存在默认超级用户，root   
鸽子拦截器 顺序处理
检查跨域
检查Token
检查权限
~~~
    1. 
    
    1.创建必须存在的几张系统表
    2.扫描全部资源，保存到数据库
    


    RBACInitialize
    扫描所有资源，更新数据库
    扫描所有角色注解，配置角色资源            
    读取SecurityConfig的配置项，配置权限和拦截规则
    


~~~
###SSO
~~~
五张表和表中必须存在的字段
1.角色表 sys_role （id,name）
2.用户表 sys_user（id,account,password）
3.资源表 sys_resource（id,resource_url）
4.角色<n-n>用户 关联表 sys_link_role_user（role_id,user_id）
5.角色<n-n>资源 关联表 sys_link_role_resource（role_id,resource_url）
~~~