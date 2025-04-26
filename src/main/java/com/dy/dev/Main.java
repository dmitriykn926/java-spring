package com.dy.dev;

import com.dy.dev.autowire.DaoService;
import com.dy.dev.config.AppConfig;
import com.dy.dev.config.EnvironmentProperties;
import com.dy.dev.dao.CompanyRepository;
import com.dy.dev.dao.UserRepository;
import com.dy.dev.dto.ConnectionPool;
import com.dy.dev.listener.dto.CompanyReadDto;
import com.dy.dev.service.CompanyService;
import com.dy.dev.service.UserService;
import com.dy.dev.service.inheritance.TimeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
//        UserRepository userRepository = context.getBean(UserRepository.class);

        TimeService timeService = context.getBean(TimeService.class);
        timeService.getServiceInfo();

//        applicationContext();
    }

    private static void applicationContext() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
//            CrudRepository companyRepository = context.getBean(CrudRepository.class);
//            PropertySourcesPlaceholderConfigurer

//            //TODO. If Profiles are used
//            context.register(AppConfig.class);
//            context.getEnvironment().setActiveProfiles("dev");
//            context.refresh();

            CompanyService companyService = context.getBean(CompanyService.class);
            Optional<CompanyReadDto> companyById = companyService.findById(123);


        }
    }
}