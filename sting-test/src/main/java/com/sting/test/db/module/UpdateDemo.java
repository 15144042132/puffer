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

import java.util.Arrays;
import java.util.HashMap;

/**
 * * 1...字符串操作
 * * 2...实体类，根据ID修改
 * * 3...条件构造器 +实体类
 * * 4...纯条件构造器，修改
 * * 5...Map 修改
 * * 6...Map 批量修改
 * * 7...实体类 批量修改
 * <p>
 * 不推荐在项目中使用 dao的字符串操作方法
 *
 * @author WangYongJi
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PufferTestApplication.class)
public class UpdateDemo {
    @Autowired
    private StDao dao;

    /**
     * Update操作
     * 1...字符串操作
     * 2...实体类，根据ID修改
     * 3...条件构造器 +实体类
     * 4...纯条件构造器，修改
     * 5...Map 修改
     * 6...Map 批量修改
     * 7...实体类 批量修改
     */
    @Test
    public void update() {

        //字符串操作
        long update1 = dao.update("update sys_user set  name='9999999' where id!=0");

        //实体类，根据ID修改
        SysUser sysUser = new SysUser();
        sysUser.setId("134");
        sysUser.setName("88888888");
        long update2 = dao.updateById(sysUser);

        //条件构造器+实体类
        SysUser sysUser2 = new SysUser();
        sysUser2.setName("7777777");
        StWrapper<SysUser> wrapper = new StWrapper<>(SysUser.class)
                .eq("id", 136);
        long update3 = dao.update(sysUser2, wrapper);


        //纯条件构造器
        StWrapper<SysUser> wrapper2 = new StWrapper<>(SysUser.class);
        wrapper2.set("name", "6666666666");
        wrapper2.setSql(" account = '999' ");
        wrapper2.eq("id", 137);
        long update4 = dao.update(wrapper2);


        //Map操作
        SysUser sysUser3 = new SysUser();
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name", "5555555555555555");
        sysUser3.setName("5555555555");
        long update5 = dao.update("sys_user", "138", objectObjectHashMap);
        long update6 = dao.update(SysUser.class, "138", objectObjectHashMap);

        //Map批量修改
        long update7 = dao.updateByIds("sys_user", Arrays.asList(1, 2, 3, 4, 5), objectObjectHashMap);
        long update8 = dao.updateByIds(SysUser.class, Arrays.asList(1, 2, 3, 4, 5), objectObjectHashMap);


        //实体类批量修改
        SysUser sysUser10 = new SysUser();
        SysUser sysUser11 = new SysUser();
        SysUser sysUser12 = new SysUser();
        sysUser10.setId("10");
        sysUser11.setId("11");
        sysUser12.setId("12");
        sysUser12.setName("10");
        sysUser11.setName("11");
        sysUser12.setName("12");


        long update9 = dao.updateBatchById(Arrays.asList(sysUser10, sysUser11, sysUser12));
        long update10 = dao.updateBatchById(Arrays.asList(sysUser10, sysUser11, sysUser12), 100);


    }


}
