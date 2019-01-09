package com.atguigu.gulixueyuan.aliyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author helen
 * @since 2018/12/14
 */
@SpringBootApplication
//@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class AliyunApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunApplication.class, args);
    }
}
