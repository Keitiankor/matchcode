package com.multicampus.matchcode.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@EnableAsync
@Configuration
public class TaskExecutorConfig {

    @Bean
    public Executor executor() {
        return new ThreadPoolExecutor(1, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
    }
}
