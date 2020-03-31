package com.linkjb.servicews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
public class ServiceWsApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceWsApplication.class, args);
    }

}
