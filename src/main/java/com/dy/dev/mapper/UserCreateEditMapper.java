package com.dy.dev.mapper;

import com.dy.dev.dao.CompanyRepository;
import com.dy.dev.dto.UserCreateEditDto;
import com.dy.dev.dto.entity.Company;
import com.dy.dev.dto.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateEditDto object, User toObject) {
        copyUser(object, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copyUser(object, user);
        return user;
    }

    private void copyUser(UserCreateEditDto object, User user) {
        user.setUsername(object.username());
        user.setFirstname(object.firstname());
        user.setLastname(object.lastname());
        user.setBirthDate(object.birthDate());
        user.setRole(object.role());
        user.setCompany(getCompany(object.companyId()));
    }

    private Company getCompany(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }
}
