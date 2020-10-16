package com.sting.test.db;

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
import java.util.HashMap;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class CountDemo {
    @Autowired
    private StDao dao;

    /**
     * delete 操作
     * ...字符串操作
     * ...条件构造器
     * ...按照ID删除
     * ...按照IDS删除
     * ...按照Map条件删除
     */
    @Test
    public void delete() {
        //字符串操作
        dao.count("select count(1) from sys_user");
        dao.count(SysUser.class);
        dao.count(SysUser.class);

        //条件构造器
        dao.delete(new StWrapper<>(SysUser.class).eq("id", 9999));

        //按照ID删除
        dao.deleteById("sys_user", 9999);
        dao.deleteById(SysUser.class, 9999);

        //按照IDS删除
        dao.deleteByIds("sys_user", Arrays.asList(9999));
        dao.deleteByIds(SysUser.class, Arrays.asList(9999));

        //按照Map条件删除
        HashMap<String, Object> mapCondition = new HashMap<>();
        mapCondition.put("id", 9999);
        dao.deleteByMap("sys_user", mapCondition);
        dao.deleteByMap(SysUser.class, mapCondition);


    }


}
