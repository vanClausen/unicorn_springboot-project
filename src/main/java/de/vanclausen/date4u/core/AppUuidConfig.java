package de.vanclausen.date4u.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AppUuidConfig {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public String appUuid() {
        String uuid = UUID.randomUUID().toString();
        log.info("Generated UUID -> {}", uuid);
        return uuid;
    }

    @Bean
    public String shorterAppUuid() {
        String shorterUuid = appUuid().substring(0, appUuid().length() / 2);
        log.info("Shortened UUID -> {}", shorterUuid);
        return shorterUuid;
    }
}
