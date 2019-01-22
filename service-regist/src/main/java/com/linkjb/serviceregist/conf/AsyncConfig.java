package com.linkjb.serviceregist.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author sharkshen
 * @data 2019/1/18 23:52
 * @Useage 定时任务配置类
 */
@Configuration //表明该类是一个配置类
@EnableAsync //开启异步事件的支持
public class AsyncConfig {
    @Value("${pool.corePoolSize}")
    private String corePoolSize ;
    @Value("${pool.maxPoolSize}")
    private String maxPoolSize ;
    @Value("${pool.queueCapacity}")
    private String queueCapacity ;
    @Autowired
    private AsyncConfig conf;

//    @Bean
    //TODO Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Integer.parseInt(corePoolSize));
        executor.setMaxPoolSize(Integer.parseInt(maxPoolSize));
        executor.setQueueCapacity(Integer.parseInt(queueCapacity
        ));
        executor.initialize();
        return executor;
    }

    @Override
    public  String toString() {
        return "AsyncConfig{" +
                "corePoolSize='" + corePoolSize + '\'' +
                ", maxPoolSize='" + maxPoolSize + '\'' +
                ", queueCapacity='" + queueCapacity + '\'' +
                '}';
    }
}
