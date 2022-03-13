package com.example;

import com.example.entity.DummyMessage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RabbitmqDemoAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemoAdvancedApplication.class, args);
    }

}
