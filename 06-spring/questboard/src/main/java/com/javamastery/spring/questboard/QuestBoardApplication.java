package com.javamastery.spring.questboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Clock;

@SpringBootApplication
// TODO: включи кэширование и планировщик
public class QuestBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestBoardApplication.class, args);
    }

    // TODO: бин Clock
}
