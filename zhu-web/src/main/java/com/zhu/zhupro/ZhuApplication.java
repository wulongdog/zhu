package com.zhu.zhupro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("com.zhu.zhupro.mapper")
@EnableCaching
public class ZhuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhuApplication.class, args);
    }
}
