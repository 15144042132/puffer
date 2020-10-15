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

import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class SelectDemo {
    @Autowired
    private StDao dao;

    /**
     * 1. selectOne
     * 三种用法
     */
    @Test
    public void selectOne() {

        //字符串查询
        SysUser sysUser1 = dao.selectOne("select * from sys_user where id!=0", SysUser.class);
        sysUser1.print();

        //条件构造器查询
        StWrapper<SysUser> wrapper = new StWrapper<>(SysUser.class).ne("id", 0);
        SysUser sysUser = dao.selectOne(wrapper);
        sysUser.print();

        //字符串查询
        Map<String, Object> stringObjectMap = dao.selectOne("select * from sys_user where id!=0");
        log.info(JSON.toJSONString(stringObjectMap));

    }

    /**
     * 2. selectObj
     * 两种用法
     */
    @Test
    public void selectObj() {
        //        只查询ID字段
        StWrapper<SysUser> wrapper = new StWrapper<>(SysUser.class).select("id").ne("id", 0);
        Object id = dao.selectObj(wrapper);
        log.info("id " + id);

        //        只查询ID字段
        Object o = dao.selectObj("select * from sys_user where id!=0 ");
        log.info("o " + o);
    }

  /**
     * 3. selectMap 和 selectById
     * 两种用法
     */
    @Test
    public void selectMap_selectById() {
        //        只查询ID字段
        StWrapper<SysUser> wrapper = new StWrapper<>(SysUser.class).select("id").ne("id", 0);
        Map<String, Object> stringObjectMap = dao.selectMap(wrapper);
        SysUser sysUser = dao.selectById(SysUser.class, 1);
        log.info("stringObjectMap " +JSON.toJSONString(stringObjectMap) );
        log.info("sysUser " +JSON.toJSONString(sysUser) );
    }




  /**
     * 4. list
     * 两种用法
     */
    @Test
    public void list() {
        //只查询ID字段
        StWrapper<SysUser> wrapper = new StWrapper<>(SysUser.class).select("id").ne("id", 0);
        Map<String, Object> stringObjectMap = dao.selectMap(wrapper);
        SysUser sysUser = dao.selectById(SysUser.class, 1);
        log.info("stringObjectMap " +JSON.toJSONString(stringObjectMap) );
        log.info("sysUser " +JSON.toJSONString(sysUser) );
    }





    public void clearDb() {
        dao.delete("delete  from sys_user where 1=1");
    }

}
