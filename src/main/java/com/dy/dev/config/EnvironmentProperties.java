package com.dy.dev.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "db")
public record EnvironmentProperties(String username,
                                    String url,
                                    String driver,
                                    List<String> hosts,
                                    List<PoolProperties> pools) {

    public static final String URL = "url";

    public EnvironmentProperties(String username, String url, String driver,
                                 List<String> hosts, List<PoolProperties> pools) {
        try {
            this.username = username;
            this.url = url;
            this.driver = driver;
            this.hosts = hosts;
            this.pools = pools;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void method() {
        System.out.println("method");
    }

    public record PoolProperties(String size, String timeout) {}
}
