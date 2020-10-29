###权限管理系统基础
~~~
1.基础表 (src/resource/sql/security.sql)
    角色表 sys_role，必备字段 id、name、role_key
                          
    用户表 sys_user，必备字段 id、account、password、phone、email、status

    资源表 sys_resource，必备字段 id、name、url、details

    角色<n-n>用户 关联表，必备字段 role_id、user_id

    角色<n-n>资源 关联表，必备字段 role_id、resource_id
2.流程
    项目启动后，扫描所有Res注解，根据URL增量更新资源表
    读取SecurityConfig的配置项，配置拦截权限规则  
 
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