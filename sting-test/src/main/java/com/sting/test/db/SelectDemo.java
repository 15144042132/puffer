package com.sting.test.db;

import com.alibaba.fastjson.JSON;
import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper;
import com.sting.test.PufferTestApplication;
import com.sting.test.db.entity.SysUser;
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

        //ByIds
        List<SysUser> list3 = dao.list(SysUser.class, Arrays.asList(1, 2, 3, 4));

        //输出测试结果
        log.info("字符串查询 " + JSON.toJSONString(list));
        log.info("实体类查询 " + JSON.toJSONString(list1));
        log.info("条件构造器查询 " + JSON.toJSONString(list2));
        log.info("ByIds " + JSON.toJSONString(list3));
    }


    /**
     * 4. listMap~~~三种用法
     * <p>
     * 5.1字符串查询
     * 5.2实体类查询
     * 5.3条件构造器查询
     */
    @Test
    public void listMap() {
        //字符串查询
        List<Map<String, Object>> maps1 = dao.listMap("select * from sys_user where id != 0");

        //实体类查询
        List<Map<String, Object>> maps2 = dao.listMap(SysUser.class);

        //条件构造器查询
        StWrapper<SysUser> wrapper =
                new StWrapper<>(SysUser.class)
                        .select("id")
                        .ne("id", 0);
        List<Map<String, Object>> maps3 = dao.listMap(wrapper);

        //输出测试结果
        log.info("字符串查询 " + JSON.toJSONString(maps1));
        log.info("实体类查询 " + JSON.toJSONString(maps2));
        log.info("条件构造器查询 " + JSON.toJSONString(maps3));
    }

    /**
     * 6. listObject~~~查询一个字段，返回Object
     * <p>
     * 6.字符串查询
     * 6.条件构造器查询
     */
    @Test
    public void listObj() {
        //字符串查询
        List<Object> objects1 = dao.listObj("select name from sys_user where id != 0");

        //条件构造器查询
        StWrapper<SysUser> wrapper =
                new StWrapper<>(SysUser.class)
                        .select("name")
                        .ne("id", 0);
        List<Object> objects3 = dao.listObj(wrapper);

        //输出测试结果
        log.info("字符串查询   " + JSON.toJSONString(objects1));
        log.info("条件构造器查询   " + JSON.toJSONString(objects3));
    }


    public void clearDb() {
        dao.delete("delete  from sys_user where 1=1");
    }

}
