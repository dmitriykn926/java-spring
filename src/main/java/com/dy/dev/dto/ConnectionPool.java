package com.dy.dev.dto;

import com.dy.dev.dao.CrudRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
//@Scope(value = BeanDefinition.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class ConnectionPool {

    @Value("#{T(java.util.Arrays).asList('1', '2', '3')}")
    private List<String> strings;

    @Value("#{T(java.util.Map).of('1', '123')}")
    private Map<String, String> map;

//    private final Map<String, CrudRepository> crudRepositoryMap;

    @Value("${db.pool.size}")
    private Integer poolSize;

    @Value("${spring.datasource.username}")
    private String userName;

    //TODO. @PostConstruct and PreDestroy are located inside jakarta-annotation-api
    @PostConstruct
    private void init() {
        log.info("initilize connection pool");
//        System.out.println(crudRepositoryMap);
        log.info("List of string: " + strings);
    }


    @PreDestroy
    private void destroy() {
        System.out.println("destroy connection pool");
    }

}
