package com.unipi.informatics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.unipi.informatics.*")
public class ConvexHullGA_Application {

    public static void main(String[] args) {
        SpringApplication.run(ConvexHullGA_Application.class, args);
    }

}

