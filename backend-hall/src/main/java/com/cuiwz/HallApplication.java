package com.cuiwz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author cuiwz
 * @Date 2020/5/19 10:38
 */
@EnableDiscoveryClient
@SpringBootApplication
public class HallApplication {

    public static void main(String[] args) {
        SpringApplication.run(HallApplication.class, args);
    }

}
