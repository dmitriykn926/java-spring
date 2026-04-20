package com.dy.dev.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName = "prototype")
public class MyPrototype {

    public void getInfo() {
        System.out.println("MyPrototype: " + getClass());
    }
}
