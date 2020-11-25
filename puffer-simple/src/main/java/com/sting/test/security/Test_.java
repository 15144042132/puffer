package com.sting.test.security;

import com.sting.db.dao.StDao;
import com.sting.security.rbac.config.SecurityConfig;
import com.sting.test.PufferTestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class Test_ {
    @Autowired
    private StDao dao;
    @Autowired
    private SecurityConfig securityConfig;

    /**
     * Count 操作
     */
    @Test
    public void count() {
        log.info("count  " + securityConfig.systemSecret());
    }


}
