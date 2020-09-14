package com.sting.test.db;

import com.sting.db.dao.StDao;
import com.sting.test.PufferTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class DeleteDemo {
    @Autowired
    private StDao dao;

    @Test
    public void insert() {

        System.out.println(dao);
        User user = new User();
        user.setName("测试数据");
        long insert1 = dao.insert(user);
        System.out.println(1);





    }


}
