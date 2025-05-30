package com.example.jobfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.jobfinder")
public class JobfinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobfinderApplication.class, args);
    }
}