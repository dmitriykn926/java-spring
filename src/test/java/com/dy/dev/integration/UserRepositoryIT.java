package com.dy.dev.integration;

import com.dy.dev.annotation.IT;
import com.dy.dev.dao.UserRepository;
import com.dy.dev.dto.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
public class UserRepositoryIT {

    private final UserRepository userRepository;

    @Test
    void findUserById() {
        Optional<User> user = userRepository.findById(1);
        assertTrue(user.isPresent());
        System.out.println(user.get());
    }
}
