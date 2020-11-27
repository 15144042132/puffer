package com.sting.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.sting.core")
public class StingCoreAutoConfiguration {
    @Autowired
    private ApplicationContext applicationContext;

    StingCoreAutoConfiguration() {
        System.out.println("");
    }

}
