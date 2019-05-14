package com.linkjb.servicewebsocket;

import com.linkjb.servicewebsocket.service.MyHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceWebsocketApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ServiceWebsocketApplication.class, args);


        ConfigurableApplicationContext applicationContext = SpringApplication.run(ServiceWebsocketApplication.class, args);
        MyHandler.setApplicationContext(applicationContext);

    }

}
