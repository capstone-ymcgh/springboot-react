package com.capstone.capstone_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // BaseTimeEntiry
@SpringBootApplication
public class CapstoneProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CapstoneProjectApplication.class, args);
    }
}
