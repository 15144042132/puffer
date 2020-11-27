package com.puffer.admin;

import com.puffer.admin.module.system.mapper.SimpleMapper;
import com.sting.core.util.ContextKit;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("**.mapper")
@SpringBootApplication
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
        //testDbMapper();
    }

    /**
     * 测试SimpleMapper
     */
    private static void testDbMapper() {
        SimpleMapper bean = ContextKit.getBean(SimpleMapper.class);


    }


}
