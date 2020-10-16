package com.sting.test.db;

import com.alibaba.fastjson.JSON;
import com.sting.db.dao.StDao;
import com.sting.db.entity.StPage;
import com.sting.db.wrapper.StWrapper;
import com.sting.test.PufferTestApplication;
import com.sting.test.db.entity.SysLinkRoleUser;
import com.sting.test.db.entity.SysRole;
import com.sting.test.db.entity.SysUser;
import com.sting.test.db.entity.SysUserJoin;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class SelectDemo {
    @Autowired
    private StDao dao;

    /**
     * 1.查询一条数据
     * ...selectOne 返回实体类
     * ...selectOne 返回Map
     * ...selectOne 返回Object,只能查询一个字段
     */
    @Test
    public void selectOne() {
        /**
         * 1.返回实体类
         */
        //字符串查询--返回实体类
        SysUser sysUser1 = dao.selectOne("select * from sys_user where id!=0", SysUser.class);

        //条件构造器查询--返回实体类
        StWrapper<SysUser> wrapper1 = new StWrapper<>(SysUser.class).select("id,name").ne("id", 0);
        SysUser sysUser2 = dao.selectOne(wrapper1);

        //根据ID查询--返回实体类
        SysUser sysUser3 = dao.selectOne(SysUser.class, 1);

        /**
         * 2.返回Map
         */
        //字符串查询--返回Map
        Map<String, Object> map1 = dao.selectMap("select * from sys_user where id!=0");

        //条件构造器查询--返回Map
        StWrapper<SysUser> wrapper2 = new StWrapper<>(SysUser.class).select("id,name").ne("id", 0);
        Map<String, Object> map2 = dao.selectMap(wrapper2);

        //根据ID查询--返回Map
        Map<String, Object> map3 = dao.selectMap(SysUser.class, 1);


        /**
         * 3.返回Object
         */
        //查询name字段
        StWrapper<SysUser> wrapper = new StWrapper<>(SysUser.class).select("name").ne("id", 0);
        Object obj1 = dao.selectObj(wrapper);

        //查询name字段
        Object obj2 = dao.selectObj("select name from sys_user where id!=0 ");


        //
        //
        //
        //输出测试结果
        log.info("字符串查询--返回实体类 " + JSON.toJSONString(sysUser1));
        log.info("条件构造器查询--返回实体类 " + JSON.toJSONString(sysUser2));
        log.info("根据ID查询--返回实体类 " + JSON.toJSONString(sysUser3));

        log.info("字符串查询--返回Map " + JSON.toJSONString(map1));
        log.info("条件构造器查询--返回Map " + JSON.toJSONString(map2));
        log.info("根据ID查询--返回Map " + JSON.toJSONString(map3));

        log.info("查询name字段--返回Object " + JSON.toJSONString(obj1));
        log.info("查询name字段--返回Object " + JSON.toJSONString(obj2));
    }

    /**
     * 2. 查询集合数据
     * ... 返回实体类
     * ... 返回Map
     * ... 返回Object,只能查询一个字段
     */
    @Test
    public void list() {
        /**
         * 1.返回实体类
         */
        //ByIds
        List<SysUser> list3 = dao.list(SysUser.class, Arrays.asList(1, 2, 3, 4));

        //字符串查询
        List<SysUser> list = dao.list(SysUser.class, "select * from sys_user where id != 0");

        //实体类查询
        List<SysUser> list1 = dao.list(SysUser.class);

        //条件构造器查询
        StWrapper<SysUser> wrapper =
                new StWrapper<>(SysUser.class)
                        .select("id")
                        .ne("id", 0);
        List<SysUser> list2 = dao.list(wrapper);

        /**
         * 2.返回Map
         */
        //字符串查询
        List<Map<String, Object>> maps1 = dao.listMap("select * from sys_user where id != 0");

        //实体类查询
        List<Map<String, Object>> maps2 = dao.listMap(SysUser.class);

        //条件构造器查询
        StWrapper<SysUser> wrapper2 =
                new StWrapper<>(SysUser.class)
                        .select("id")
                        .ne("id", 0);
        List<Map<String, Object>> maps3 = dao.listMap(wrapper2);

        /**
         * 3.返回Object,只能查询一个字段
         */
        //字符串查询
        List<Object> objects1 = dao.listObj("select name from sys_user where id != 0");

        //条件构造器查询
        StWrapper<SysUser> wrapper3 =
                new StWrapper<>(SysUser.class)
                        .select("name")
                        .ne("id", 0);
        List<Object> objects3 = dao.listObj(wrapper3);


        //输出测试结果
        log.info("字符串查询--返回实体类 " + JSON.toJSONString(list));
        log.info("泛型查询--返回实体类 " + JSON.toJSONString(list1));
        log.info("条件构造器查询--返回实体类 " + JSON.toJSONString(list2));
        log.info("ByIds--返回实体类 " + JSON.toJSONString(list3));

        log.info("字符串查询--返回Map " + JSON.toJSONString(maps1));
        log.info("泛型查询--返回Map " + JSON.toJSONString(maps2));
        log.info("条件构造器查询--返回Map " + JSON.toJSONString(maps3));

        log.info("字符串查询-返回Object,只能查询一个字段   " + JSON.toJSONString(objects1));
        log.info("条件构造器查询-返回Object,只能查询一个字段   " + JSON.toJSONString(objects3));
    }

    /**
     * 3. 分页 Page，默认第1页，查询10条数据
     */
    @Test
    public void page() {
        StPage<SysUser> page1 = dao.page(new StPage<>(), SysUser.class);
        StPage<SysUser> page2 = dao.page(new StPage<>(1, 10), SysUser.class);

        StPage<SysUser> page3 = dao.page(
                new StPage<>(1, 10),
                new StWrapper<>(SysUser.class).ne("id", 0)
        );

        StPage<SysUser> page4 = dao.page(
                new StPage<>(1, 10),
                SysUser.class,
                "select * from sys_user where id !=0"
        );


        //输出测试结果
        log.info("默认第一页，无条件--page1 " + JSON.toJSONString(page1));
        log.info("第一页，无条件--page2    " + JSON.toJSONString(page2));
        log.info("条件构造器--page3       " + JSON.toJSONString(page3));
        log.info("字符串--page4          " + JSON.toJSONString(page4));
    }

    /**
     * 4. join 操作
     */
    @Test
    public void join() {
        StWrapper<SysUserJoin> wrapper = new StWrapper<>(SysUserJoin.class);

        //leftJoin
        wrapper.leftJoin(SysRole.class);
        wrapper.on("1","1");

        //leftJoin
        wrapper.leftJoin(SysLinkRoleUser.class);
        wrapper.on("1","1");

        //leftJoin
        wrapper.leftJoin("sys_log_login");
        wrapper.on("1","1");

        List<SysUserJoin> list = dao.list(wrapper);
        log.info("join 操作          " + JSON.toJSONString(list));
    }

    public void clearDb() {
        dao.delete("delete  from sys_user where 1=1");
    }

}
