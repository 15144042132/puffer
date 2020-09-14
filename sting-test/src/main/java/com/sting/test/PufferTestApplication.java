package com.sting.test;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan(basePackages = {"com.sting.db.mapper"})
@SpringBootApplication(scanBasePackages = {"com.sting"})
public class PufferTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PufferTestApplication.class, args);

    }

}
