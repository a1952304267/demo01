package com.dearwang.store01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//自动查找mapper
@MapperScan("com.dearwang.store01.mapper")
public class Store01Application {

    public static void main(String[] args) {
        SpringApplication.run(Store01Application.class, args);
    }

}
