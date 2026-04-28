package com.dy.dev.util;

import com.dy.dev.dto.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class CriteriaUtil {

    public static Specification<User> firstNameLike(String firstName) {
        return (root, query, criteriaBuilder) -> {
            if (firstName == null || firstName.isBlank()) {
                return null;
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("firstName")),
                    "%" + firstName.toLowerCase() + "%"
            );
        };
    }

    public static Specification<User> lastNameLike(String lastName) {
        return (root, query, criteriaBuilder) -> {
            if (lastName == null || lastName.isBlank()) {
                return null;
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("lastName")),
                    "%" + lastName.toLowerCase() + "%"
            );
        };
    }

    public static Specification<User> birthDateBefore(LocalDate birthDate) {
        return (root, query, criteriaBuilder) -> {
            if (birthDate == null) {
                return null;
            }

            return criteriaBuilder.lessThan(root.get("birthDate"), birthDate);
        };
    }
}
