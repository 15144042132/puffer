package com.sting.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.sting.db")
@MapperScan("com.sting.db.mapper")
public class StingDbAutoConfiguration {
    StingDbAutoConfiguration(){
        System.out.println("");
    }
}
