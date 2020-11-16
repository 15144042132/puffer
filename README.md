# 更新日志

### 实现思路
接口、抽象类 + 默认实现 + 条件判断注入

### [v0.0.1]
~~~
1.重写Wrapper条件构造器,继承 AbstractWrapper 并将 QueryWrapper和 UpdateWrapper整合在一起,,并做出适当增强
2.升级MybatisPlus到3.4.0（作者有点小强迫症啊....把class类型等号赋值取值，变成了set方法赋值取值.....）
3.增强Wrapper 添加多表联查功能
   /**
    * 多表联查
    * join
    * left join
    * right join
    *
    * @author WangYongJi
    * @date 2020/10/12 9:40
    */
   public interface StJoin<Children, R> extends Serializable {
4.修改条件构造器，测试insert操作，添加通用的 String/Entity/Map/List<Entity>/List<Map> 插入操作
5. 添加test模块的对于数据库Insert操作的Demo
    (1)插入 实体类
    (2)插入 纯字符串SQL
    (3)插入 Map
    (4)批量插入 实体类
    (5)插入或者修改，判断条件是，ID是否存在,或者条件构造器Wrapper
    (6)批量插入或修改，条件是，ID是否存在
6.  /**
       * 1.查询一条数据
       * ...selectOne 返回实体类
       * ...selectOne 返回Map
       * ...selectOne 返回Object,只能查询一个字段
       */
     /**
       * 2. 查询集合数据
       * ... 返回实体类
       * ... 返回Map
       * ... 返回Object,只能查询一个字段
       */
     /**
       * 3. 分页 Page，默认第1页，查询10条数据
       */
     /**
       * 4. join 操作
       */
7.     * Update操作
       * 1...字符串操作
       * 2...实体类，根据ID修改
       * 3...条件构造器 +实体类
       * 4...纯条件构造器，修改
       * 5...Map 修改
       * 6...Map 批量修改
       * 7...实体类 批量修改
8.     * delete 操作
       * ...字符串操作
       * ...条件构造器
       * ...按照ID删除
       * ...按照IDS删除
       * ...按照Map条件删除
9.复杂操作， 级联对象赋值 案例编写
   Results（{Result}）
   @One使用方式
   @May使用方式
10.统一插件版本号
11.优化 Res注解和Handler处理程序
12. 添加两个角色功能注解，用于初始化时，可以自动将资源与角色关联（也可配置在Security配置文件中）
    * Role
    * 角色注解--注明当前资源数，属于哪些角色
    * <p>
    * Role 小于 RoleExclude
    
     * RoleExclude
     * 排除注解--注明当前资源数，不属于哪些角色
     * <p>
     * Role 小于 RoleExclude

13.声明角色资源 @Role
   排除角色资源 @RoleExclude
   角色资源处理器 @RoleHandler

14.RBACInitialize 构造函数注入异常修复
   修改实体类名称，把配置项放到一个类中
   避免类名与其他框架重复，修改类名

15.权限模块修改，最终决定，权限模块设置为3块
1.必要的表结构
2.初始化状态
3.高度配置

~~~



