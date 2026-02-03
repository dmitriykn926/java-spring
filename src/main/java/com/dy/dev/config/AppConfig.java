package com.dy.dev.config;

import com.dy.dev.autowire.DaoService;
import com.dy.dev.dao.CrudRepository;
import com.dy.dev.dao.UserRepository;
import com.dy.dev.dto.ConnectionPool;
import com.dy.dev.service.ClientService;
import com.dy.web.config.WebConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.List;

//@ImportResource("classpath:applicationContext.xml")
@Import({WebConfig.class})
//@AutoConfigureBefore
@Configuration(proxyBeanMethods = false, enforceUniqueMethods = false)
@ComponentScan(basePackages = "com.dy.dev",
        resourcePattern = "**/*.class",
        nameGenerator = BeanNameGenerator.class,
        scopedProxy = ScopedProxyMode.DEFAULT,
        scopeResolver = AnnotationScopeMetadataResolver.class,
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Component.class}),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {CrudRepository.class}),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\..+Repository"),
        })
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public final class AppConfig {

        @Bean
        private DaoService daoServicePrivate() {
                return new DaoService();
        }

        @Bean("daoServiceV1")
        public DaoService daoServices() {
                return new DaoService();
        }

        @Bean("daoServiceV2") //TODO Allows when enforceUniqueMethods enabled
        public DaoService daoServices(List<DaoService> daoServices) {
                return new DaoService();
        }

        @Bean(defaultCandidate = false)
        public ClientService clientServicePrivate() {
                return new ClientService();
        }

        @Bean
        public ClientService clientServices(List<ClientService> clientServices) {
                return new ClientService();
        }


//        @Bean(
//                name = "jtaManager, transactionManager"
//        )
//        public PlatformTransactionManager transactionManager() {
//                return new JpaTransactionManager();
//        }

        //Beans created using IService locator
//        @Bean("pool")
//        @Profile("!prod")
//        // ! & |
//        public ConnectionPool pool() {
//                return new ConnectionPool();
//        }

//        @Bean
//        public UserRepository userRepository(@Qualifier("pool") ConnectionPool pool) {
//                return new UserRepository(pool);
//        }
//
//        @Bean
//        public UserRepository userRepository2() {
//                return new UserRepository(pool()); // As Configuration uses Proxy, we can set up beans via the method invocation
//        }
}
