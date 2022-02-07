package com.cqupt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CquptApplication {

    public static void main(String[] args) {
        System.out.println("Springboot初始化完成=======");

        SpringApplication.run(CquptApplication.class, args);
    }

}
