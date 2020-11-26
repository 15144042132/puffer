package com.sting.test;

import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan
@SpringBootApplication
public class PufferTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PufferTestApplication.class, args);
        TableInfoHelper.getTableInfos();
    }

}
