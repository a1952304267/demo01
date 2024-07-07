package com.dearwang.store01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;
import javax.xml.crypto.Data;

//标注为当前类是配置类
//@Configuration
@SpringBootApplication
//自动查找mapper
//与@Mapper用其一即可
@MapperScan("com.dearwang.store01.mapper")
public class Store01Application {

    public static void main(String[] args) {
        SpringApplication.run(Store01Application.class, args);
    }

//    注入Bean
//    @Bean
//    public MultipartConfigElement getMultipartConfigElement(){
//        /*创建一个工厂类对象*/
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        /*配置对应信息，上传大小限制与总文件大小限制*/
//        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
//        factory.setMaxRequestSize(DataSize.of(15,DataUnit.MEGABYTES));
//
//        /*返回由工厂类对象创建的element对象*/
//        return factory.createMultipartConfig();
//
//    }

}
