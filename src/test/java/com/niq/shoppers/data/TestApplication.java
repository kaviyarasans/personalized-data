package com.niq.shoppers.data;

import com.niq.shoppers.data.common.ContainersConfig;
import org.springframework.boot.SpringApplication;

public class TestApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "local");
        SpringApplication.from(PersonalizedDataApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
