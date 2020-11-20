### 应该处理
~~~
这是一个处理角色权限的小模块
只应该存在必要的逻辑，扫描插入、认证检查逻辑，提供与用户权限相关的丰富函数
~~~
## 不应该处理
~~~
用户信息、日志、其他、都不应该处理
~~~


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