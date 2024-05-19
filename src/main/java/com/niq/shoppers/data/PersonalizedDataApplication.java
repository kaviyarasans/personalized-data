package com.niq.shoppers.data;

import com.niq.shoppers.data.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class PersonalizedDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalizedDataApplication.class, args);
    }
}
