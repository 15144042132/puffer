package com.puffer.admin;

import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan(basePackages = {"com.sting.db.mapper", "com.sting.test.db.dao"})
@SpringBootApplication(scanBasePackages = {"com.sting"})
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
        TableInfoHelper.getTableInfos();
    }

}
