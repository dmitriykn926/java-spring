package com.dy.dev.integration;

import com.dy.dev.config.TestConfigurationRunner;
import com.dy.dev.dao.CompanyRepository;
import com.dy.dev.dao.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@RequiredArgsConstructor
public class FoodServiceIT {

    private final FoodRepository foodRepository;
    private final CompanyRepository companyRepository;

    @Test
    void testFindById() {
        System.out.println();

    }
}
