package com.dy.dev.config;

import com.dy.dev.dto.ConnectionPool;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

@TestConfiguration
public class TestConfigurationRunner {

    // TODO All MockBeans or SpyBeans should be declared here, to avoid multiple ApplicationContext raising

    @MockitoSpyBean(name = "connectionPool")
    private ConnectionPool connectionPool;
}
