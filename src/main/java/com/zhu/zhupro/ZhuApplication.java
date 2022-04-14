package com.zhu.zhupro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ZhuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhuApplication.class, args);
    }

}
