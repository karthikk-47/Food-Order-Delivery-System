package com.foodapp.deliveryexecutive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.foodapp.deliveryexecutive"})
public class DeliveryexecutiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryexecutiveApplication.class, (String[])args);
    }
}
