package top.alexmmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 汪永晖
 */
@EnableEurekaClient
@SpringBootApplication
public class SubscriptionMailApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SubscriptionMailApplication.class, args);
    }
}
