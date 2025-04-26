package com.dy.dev.integration;

import com.dy.dev.annotation.IT;
import com.dy.dev.config.TestConfigurationRunner;
import com.dy.dev.dto.ConnectionPool;
import com.dy.dev.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


//TODO As there are two IT test, the ApplicationContext being raised two times: 1 time for CompanyServiceIT, 2 for UserServiceIT
// to avoid that usually created a new TestConfigurationRunner which be specified inside @SpringBootTest annotation
//@SpringBootTest(classes = TestConfigurationRunner.class)
@IT //TODO VERY IMPORTANT. In case of there are some changes in different classes (for example: no @ActiveProfiles), then a new ApplicationContext will be raised
// A good practise to use the same annotation for all IT tests, then only one ApplicationContext be raised for all IT test classes
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool connectionPool;

    public UserServiceIT(UserService userService, ConnectionPool connectionPool) {
        this.userService = userService;
        this.connectionPool = connectionPool;
    }

    @Test
    void findById() {
        System.out.println("integration test");
    }
}
