package com.dy.dev.dto;

import org.springframework.beans.factory.annotation.Value;

//All getters should be matched with table column names
public interface PersonalInfo2 {

    String getFirstname();

    String getLastname();

    String getBirthDate();

    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();
}
