package com.dy.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class AuditConfiguration {

    //TODO. Provided is needed to configure CreatedBy and ModifiedBy
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("dydev");
    }
}
