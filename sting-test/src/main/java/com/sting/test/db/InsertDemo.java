package com.sting.test.db;

import com.sting.db.dao.StDao;
import com.sting.test.PufferTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class InsertDemo {
    @Autowired
    private StDao dao;

    @Test
    public void insert() throws Exception {
        /**
         * long insert(P entity);
         */
        User user = new User();
        user.setName("测试数据111");
        user.setDate(LocalDate.now());
        user.setTime(LocalTime.now());
        user.setDateTime(LocalDateTime.now());
        long insert = dao.insert(user);
        System.out.println(user.toString());
        System.out.println("操作数据条数 " + insert);

        /**
         * insert(String fullSqlString);
         */
        String fullSqlString = "insert into user (name) values('222222')";
        long insert1 = dao.insert(fullSqlString);
        System.out.println("操作数据条数 " + insert1);

        /**
         * insert(String tableName, Map<String, Object> map);
         */
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "33333");
        long user1 = dao.insert("user", map);
        System.out.println("操作数据条数 " + user1);

        /**
         *
         *<P extends StEntity> long insertBatch(List<P> entityList);
         *
         *<P extends StEntity> long insertBatch(List<P> entityList, int batchSize);
         */
        ArrayList<User> objects = new ArrayList<>();
        User user3 = new User();
        User user4 = new User();
        user3.setName("user3");
        user4.setName("user4");
        user4.setTime(LocalTime.now());

        objects.add(user3);
        objects.add(user4);
        long l1 = dao.insertBatch(objects);
        //long l2 = dao.insertBatch(objects,300);
        System.out.println("insertBatch--操作数据条数 " + l1);


        /**
         *
         */
        User user5 = new User();
        user5.setId(user3.getId());
        user5.setId(user3.getName() + "我被修改了呜呜呜呜...");
        long l = dao.insertOrUpdate(user5);


        /**
         *
         *<P extends StEntity> long insertBatch(List<P> entityList);
         *
         *<P extends StEntity> long insertBatch(List<P> entityList, int batchSize);
         */
        ArrayList<User> objects2 = new ArrayList<>();
        User user7 = new User();
        User user8 = new User();
        user3.setName("user7");
        user4.setName("user8");
        user4.setTime(LocalTime.now());
        dao.insertOrUpdateBatch(objects2);


    }


}
