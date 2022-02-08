package com.cqupt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan(basePackages = {"com.cqupt.mapper"})
public class CquptApplication {

    public static void main(String[] args) {
        System.out.println("Springboot初始化完成=======");
        SpringApplication.run(CquptApplication.class, args);
    }

}
