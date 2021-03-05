package com.hogwarts.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/11
 */
@SpringBootApplication
@EnableDiscoveryClient//服务注册
@EnableFeignClients//服务调用
//默认只扫描当点项目的配置类
@ComponentScan(basePackages = {"com.hogwarts"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class);
    }
}
