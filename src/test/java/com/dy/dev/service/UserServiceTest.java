package com.dy.dev.service;

import com.dy.dev.annotation.IT;
import com.dy.dev.dao.UserRepository;
import com.dy.dev.dto.entity.Role;
import com.dy.dev.dto.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@IT
@Transactional
@RequiredArgsConstructor
public class UserServiceTest {

    private final UserRepository userRepository;

    @Test
    void checkPageable() {
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        Slice<User> users = userRepository.findAllBy(pageable);
        users.forEach(System.out::println);
    }

    @Test
    void checkSlice() {
        PageRequest pageable = PageRequest.of(1, 5, Sort.by("id"));
        Slice<User> slice = userRepository.findAllBy(pageable);

        slice.forEach(System.out::println);
        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(System.out::println);
        }
    }

    @Test
    void checkPage() {
        PageRequest pageable = PageRequest.of(0, 2, Sort.by("id"));
        Page<User> page = userRepository.findAll(pageable);

        System.out.println(page.getTotalPages() + " " + page.getTotalElements());

        page.forEach(System.out::println);
        while (page.hasNext()) {
            page = userRepository.findAll(page.nextPageable());
            page.forEach(System.out::println);
        }
    }

    @Test
    void updateUserRole() {
        User ivan = userRepository.getById(1); // here User be added to PersistentContext (Hibernate)
        assertSame(Role.ADMIN, ivan.getRole());

        //TODO There will be update as @Modifying(flushAutomatically = true)
        ivan.setBirthDate(LocalDate.of(1990, 1, 1));

        int updateCount = userRepository.updateRole(Role.USER, 1l);
        assertEquals(1, updateCount);

        // TODO. important after update user will be taken from PersistentContext
        //TODO. to resolve that, you should use @Modifying(clearAutomatically = true)
        User theSame = userRepository.getById(1);
        assertSame(Role.USER, theSame.getRole());
    }
}
