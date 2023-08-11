package com.multicampus.matchcode;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MatchcodeApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MatchcodeApplication.class)
            .properties("spring.config.location=classpath:application.yml")
            .profiles("dev")
            .run(args);
    }
}
