package com.dy.dev.dto;

import com.dy.dev.dto.entity.Role;
import com.dy.dev.listener.dto.CompanyReadDto;

import java.time.LocalDate;

public record UserReadDto(Integer id,
                          String username,
                          LocalDate birthDate,
                          String firstname,
                          String lastname,
                          Role role,
                          CompanyReadDto companyId) {

}
