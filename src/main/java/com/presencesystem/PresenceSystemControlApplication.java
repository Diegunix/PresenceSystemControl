package com.presencesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PresenceSystemControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresenceSystemControlApplication.class, args);
    }

}
