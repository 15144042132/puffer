package com.puffer.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan(basePackages = {"com.sting.db.mapper", "com.puffer.admin.**.mapper"})
@SpringBootApplication()
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
