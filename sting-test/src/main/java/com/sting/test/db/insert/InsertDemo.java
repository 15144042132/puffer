package com.sting.test.db.insert;

import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper;
import com.sting.test.PufferTestApplication;
import com.sting.test.db.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class InsertDemo {
    @Autowired
    private StDao dao;

    /**
     * 1.插入一个实体类数据
     */
    @Test
    public void insert1() {
        SysUser sysUser = new SysUser();
        sysUser.setName("test");
        sysUser.setCreateTime(LocalDateTime.now());
        long insert = dao.insert(sysUser);
        sysUser.print();

        SysUser sysUser1 = dao.selectById(SysUser.class, sysUser.getId());
        sysUser1.print();
    }

    /**
     * 2.执行纯字符串SQL
     */
    @Test
    public void insert2() {
        String fullSqlString = "insert into sys_user (name) values('222222')";
        dao.insert(fullSqlString);

        List<SysUser> name = dao.list(new StWrapper<>(SysUser.class).select("id", "name").eq("name", "222222"));
        System.out.println(name.size());
    }

    /**
     * 3.插入一个Map数据
     */
    @Test
    public void insert3() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "33333");
        long user1 = dao.insert("sys_user", map);
        List<SysUser> name = dao.list(new StWrapper<>(SysUser.class).select("id", "name").eq("name", "33333"));
        System.out.println(name.size());
    }

    @Test
    public void insertBatch1() {
    }

    @Test
    public void insertBatch2() {
    }

    @Test
    public void insertOrUpdate1() {
    }

    @Test
    public void insertOrUpdate2() {
    }

    @Test
    public void insertOrUpdateBatch1() {
    }

    @Test
    public void insertOrUpdateBatch2() {
    }

}
