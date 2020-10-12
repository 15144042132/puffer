package com.sting.test.db.insert;

import com.sting.db.dao.StDao;
import com.sting.db.wrapper.StWrapper_3_4_0;
import com.sting.test.PufferTestApplication;
import com.sting.test.db.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class InsertDemo {
    @Autowired
    private StDao dao;

    @Test
    public void insert() throws Exception {
        insert1();
        insert2();
        insert3();
        insert4();
        insert5();
        insert6();
    }

    /**
     * long insert(P entity);
     */
    @Test
    public void insert1() throws Exception {
        SysUser user = new SysUser();
        user.setName("测试数据111");
        long insert = dao.insert(user);
        System.out.println(user.toString());
        System.out.println("操作数据条数 " + insert);
    }

    /**
     * insert(String fullSqlString);
     */
    @Test
    public void insert2() throws Exception {
        String fullSqlString = "insert into sys_user (name) values('222222')";
        long insert1 = dao.insert(fullSqlString);
        System.out.println("操作数据条数 " + insert1);
    }

    /**
     * insert(String tableName, Map<String, Object> map);
     */
    @Test
    public void insert3() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "33333");
        long user1 = dao.insert("sys_user", map);
        System.out.println("操作数据条数 " + user1);
    }

    /**
     * <P extends StEntity> long insertBatch(List<P> entityList);
     * <p>
     * <P extends StEntity> long insertBatch(List<P> entityList, int batchSize);
     */
    @Test
    public void insert4() throws Exception {
        ArrayList<SysUser> objects = new ArrayList<>();
        SysUser user3 = new SysUser();
        SysUser user4 = new SysUser();
        user3.setName("user3");
        user4.setName("user4");

        objects.add(user3);
        objects.add(user4);
        long l1 = dao.insertBatch(objects);
        System.out.println("insertBatch--操作数据条数 " + l1);
    }

    /**
     *
     */
    @Test
    public void insert5() throws Exception {
        SysUser user5 = new SysUser();
        user5.setId("99");

        StWrapper_3_4_0<SysUser> sysUserQueryWrapper = new StWrapper_3_4_0<>(user5);
        SysUser entity = sysUserQueryWrapper.getEntity();
        Class<? extends StWrapper_3_4_0> aClass = sysUserQueryWrapper.getClass();

        long l = dao.insertOrUpdate(user5);
    }

    /**
     * <P extends StEntity> long insertBatch(List<P> entityList);
     * <p>
     * <P extends StEntity> long insertBatch(List<P> entityList, int batchSize);
     */
    @Test
    public void insert6() throws Exception {
        ArrayList<SysUser> objects2 = new ArrayList<>();
        SysUser user7 = new SysUser();
        SysUser user8 = new SysUser();
        objects2.add(user7);
        objects2.add(user8);
        user7.setName("user7");
        user8.setName("user8");
        dao.insertOrUpdateBatch(objects2);
    }

}
