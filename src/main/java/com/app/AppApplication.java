package com.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.app.*.dao")
@SpringBootApplication
@EnableCaching
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
        System.out.println("----------------------------------");
        System.out.println("------------start success---------");
        System.out.println("----------------------------------");
    }
}
