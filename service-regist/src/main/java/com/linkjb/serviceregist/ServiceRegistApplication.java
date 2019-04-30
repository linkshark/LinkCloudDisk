package com.linkjb.serviceregist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching //开启缓存
@EnableScheduling//开启定时任务
//@EnableAspectJAutoProxy(proxyTargetClass=true)//开启AOP
public class ServiceRegistApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistApplication.class, args);
    }

}

