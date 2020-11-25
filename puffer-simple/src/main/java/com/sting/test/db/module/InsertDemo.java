package com.sting.test.db.module;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class InsertDemo {
    @Autowired
    private StDao dao;

    /**
     * 1.插入 实体类
     */
    @Test
    public void insert1() {
        /**
         * 单条插入
         */
        SysUser sysUser = new SysUser();
        sysUser.setName("test");
        sysUser.setCreateTime(LocalDateTime.now());
        long insert = dao.insert(sysUser);
        sysUser.print();

        SysUser sysUser1 = dao.selectOne(SysUser.class, sysUser.getId());
        sysUser1.print();


        /**
         * 多条插入
         */
        ArrayList<Object> objects = new ArrayList<>();
        SysUser sysUser111 = new SysUser();
        sysUser111.setName("test111");
        SysUser sysUser222 = new SysUser();
        sysUser222.setName("test222");
        long insert1 = dao.insert(sysUser111, sysUser222);
        sysUser111.print();
        sysUser222.print();

    }

    /**
     * 2.插入 纯字符串SQL
     */
    @Test
    public void insert2() {
        String fullSqlString = "insert into sys_user (name) values('000000969696')";
        String fullSqlString1 = "insert into sys_user (name) values('111111969696')";
        String fullSqlString2 = "insert into sys_user (name) values('222222969696')";
        dao.insert(fullSqlString);
        dao.insert(fullSqlString1, fullSqlString2);

        List<SysUser> name = dao.list(new StWrapper<>(SysUser.class).select("id", "name").like("name", "969696"));
        System.out.println(name.size());
    }

    /**
     * 3.插入 Map
     */
    @Test
    public void insert3() {
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name", "33333");
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name", "33333");
        //
        dao.insert("sys_user", map1);
        dao.insert(SysUser.class, map2);
        List<SysUser> list = dao.list(new StWrapper<>(SysUser.class).select("id", "name").eq("name", "33333"));
        log.info(JSON.toJSONString(list));
        //
        log.info(JSON.toJSONString(map1));
        log.info(JSON.toJSONString(map2));
    }

    /**
     * 4.批量插入 实体类
     */
    @Test
    public void insertBatch() {
        SysUser sysUser1 = new SysUser();
        sysUser1.setName("insertBatch111");
        SysUser sysUser2 = new SysUser();
        sysUser2.setName("insertBatch222");
        SysUser sysUser3 = new SysUser();
        sysUser3.setName("insertBatch333");

        ArrayList<SysUser> objects = new ArrayList<>();
        objects.add(sysUser1);
        objects.add(sysUser2);
        objects.add(sysUser3);
        long l = dao.insertBatch(objects);
        System.out.println(objects);
        //        dao.insertBatch(objects, 1);
        List<SysUser> list = dao.list(new StWrapper<>(SysUser.class).select("id", "name").like("name", "insertBatch"));
        System.out.println(list);
    }

    /**
     * 5.批量插入或者修改，判断条件是，ID是否存在
     */
    @Test
    public void insertOrUpdate() {
        clearDb();
        //        插入或者修改
        SysUser sysUser1 = new SysUser();
        sysUser1.setName("i1111");
        sysUser1.print();
        long l1 = dao.insertOrUpdate(sysUser1);
        List<SysUser> list1 = dao.list(new StWrapper<>(SysUser.class).select("id", "name").like("name", "insertOrUpdate"));
        System.out.println(list1);
        //
        //
        //再次执行插入或者修改
        sysUser1.setName("in22222");
        long l2 = dao.insertOrUpdate(sysUser1);
        List<SysUser> list2 = dao.list(new StWrapper<>(SysUser.class).select("id", "name").like("name", "insertOrUpdate"));
        System.out.println(list2);


        //条件构造器
        String id = sysUser1.getId();
        sysUser1.setName("0000000");
        StWrapper<SysUser> wrapper = new StWrapper<>(SysUser.class).eq("id", id);
        dao.insertOrUpdate(sysUser1, wrapper);

        //
        List<SysUser> list = dao.list(SysUser.class);
        log.info(JSON.toJSONString(list));
    }

    /**
     * 6.批量插入或修改，条件是，ID是否存在
     */
    @Test
    public void insertOrUpdateBatch() {
        clearDb();
        //        插入或者修改
        SysUser sysUser1 = new SysUser();
        sysUser1.setName("insertBatch111");
        SysUser sysUser2 = new SysUser();
        sysUser2.setName("insertBatch222");
        SysUser sysUser3 = new SysUser();
        sysUser3.setName("insertBatch333");

        ArrayList<SysUser> objects = new ArrayList<>();
        objects.add(sysUser1);
        objects.add(sysUser2);
        objects.add(sysUser3);
        //此处执行插入操作，因为上面不存在ID
        dao.insertOrUpdateBatch(objects);
        //
        //
        //
        sysUser1.setName("insertBatch999");
        sysUser2.setName("insertBatch999");
        sysUser3.setName("insertBatch999");
        //此处执行修改操作，因为上面添加过后已经存在ID
        dao.insertOrUpdateBatch(objects);

        //
        List<SysUser> list = dao.list(SysUser.class);
        log.info(JSON.toJSONString(list));
    }

    public void clearDb() {
        dao.delete("delete  from sys_user where 1=1");
    }

}
