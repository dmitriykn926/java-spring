package com.dy.dev.mapper;

import com.dy.dev.dto.UserReadDto;
import com.dy.dev.dto.entity.User;
import com.dy.dev.listener.dto.CompanyReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserReadDto> {

    private final CompanyMapper companyMapper;

    @Override
    public UserReadDto map(User object) {
        CompanyReadDto companyReadDto = Optional.ofNullable(object.getCompany())
                .map(companyMapper::map)
                .orElse(null);
        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getBirthDate(),
                object.getFirstname(),
                object.getLastname(),
                object.getRole(),
                companyReadDto
        );
    }

}
