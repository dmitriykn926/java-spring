package com.dy.dev.integration;

import com.dy.dev.Main;
import com.dy.dev.annotation.IT;
import com.dy.dev.config.TestConfigurationRunner;
import com.dy.dev.listener.dto.CompanyReadDto;
import com.dy.dev.listener.events.EntityEvent;
import com.dy.dev.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@IT
//@ActiveProfiles("test") // to enable application-test.yaml
//@SpringBootTest(classes = TestConfigurationRunner.class) //TODO for Spring boot
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) //TODO enable to use Constructor and Autowire or spring.properties
//@ExtendWith(SpringExtension.class) //TODO to integrate SpringExtension with Junit
//@ContextConfiguration(classes = Main.class) //TODO set applicationContext, if this is xml, groovy, properties -> values instead of classes
//@TestPropertySource("classpath:application.properties") // TODO. IMPORTANT. Not working with *.yaml
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;

    public CompanyServiceIT(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Test
    void findById() {
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
        assertThat(getCompany(COMPANY_ID)).isEqualTo(actualResult);
    }

    private static Optional<CompanyReadDto> getCompany(int id) {
        return Optional.of(new CompanyReadDto(id));
    }
}
