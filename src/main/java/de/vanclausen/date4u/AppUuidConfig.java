package de.vanclausen.date4u;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AppUuidConfig {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public UUID uuid() {
        UUID uuid = UUID.randomUUID();
        log.info("Generated UUID -> {}", uuid);
        return uuid;
    }
}
