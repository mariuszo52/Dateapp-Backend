package com.dateapp.dateapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class DateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DateApplication.class, args);
    }

}
