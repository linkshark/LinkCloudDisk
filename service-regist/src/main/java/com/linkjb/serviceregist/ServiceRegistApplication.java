package com.linkjb.serviceregist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceRegistApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistApplication.class, args);
    }

}

