package com.dy.dev.dto;

import java.time.LocalDate;

public record UserFilter(String firstName, String lastname, LocalDate birthDate) {
}
