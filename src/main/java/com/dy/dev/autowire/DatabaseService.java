package com.dy.dev.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService {

    private final DaoService daoService;

    @Autowired
    public DatabaseService(DaoService daoService) {
        this.daoService = daoService;
    }
}
