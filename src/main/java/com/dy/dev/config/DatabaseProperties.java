package com.dy.dev.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring")
public record DatabaseProperties(
        DataSourceProperties dataSource,
        JpaProperties jpa) {


    public record DataSourceProperties(
            String url,
            String username,
            String password,
            String driver) {
    }

    public record JpaProperties(String databasePlatform) {}
}
