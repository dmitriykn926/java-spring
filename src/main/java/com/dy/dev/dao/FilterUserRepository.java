package com.dy.dev.dao;

import com.dy.dev.dto.UserFilter;
import com.dy.dev.dto.entity.User;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);

    List<User> findAllByCompanyId(Integer companyId);

    void updateUsersByCompanyId(List<User> users);

}
