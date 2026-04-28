package com.dy.dev.dto;

import com.dy.dev.annotations.UserInfo;
import com.dy.dev.dto.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@FieldNameConstants
@UserInfo
public record UserCreateEditDto(
        @Email
        String username,

        LocalDate birthDate,

        @NotNull
        String firstname,

        @NotNull
        String lastname,

        Role role,

        Integer companyId) {
}
