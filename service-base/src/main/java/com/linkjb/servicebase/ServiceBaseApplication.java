package com.linkjb.servicebase;

import com.linkjb.servicebase.controller.SpiderController;
import com.linkjb.servicebase.service.Impl.SpiderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
public class ServiceBaseApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(ServiceBaseApplication.class, args);

    }

}
