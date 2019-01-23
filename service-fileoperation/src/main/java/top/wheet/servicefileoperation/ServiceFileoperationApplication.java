package top.wheet.servicefileoperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ServiceFileoperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFileoperationApplication.class, args);
    }

}

