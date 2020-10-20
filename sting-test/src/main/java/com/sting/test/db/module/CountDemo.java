package com.sting.test.db.module;

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

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class CountDemo {
    @Autowired
    private StDao dao;

    /**
     * Count 操作
     */
    @Test
    public void count() {
        long count1 = dao.count("select count(1) from sys_user");
        long count2 = dao.count(SysUser.class);
        long count3 = dao.count(new StWrapper<>(SysUser.class).ne("id", 0));
        log.info("count1  " + count1);
        log.info("count2  " + count2);
        log.info("count3  " + count3);
    }


}
