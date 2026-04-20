package com.dy.dev.service;

import com.dy.dev.annotation.IT;
import com.dy.dev.dao.UserRepository;
import com.dy.dev.dto.PersonalInfo;
import com.dy.dev.dto.PersonalInfo2;
import com.dy.dev.dto.UserFilter;
import com.dy.dev.dto.entity.Role;
import com.dy.dev.dto.entity.User;
import com.dy.dev.integration.IntegrationBaseTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@IT
@Transactional
@RequiredArgsConstructor
public class UserServiceTest extends IntegrationBaseTest {

    private final UserRepository userRepository;

    @Test
    void testJdbcTemplate() {
        List<User> allByCompanyId = userRepository.findAllByCompanyId(1);
        assertThat(allByCompanyId).isNotNull();
        allByCompanyId.forEach(System.out::println);
    }

    @Test
    void checkAuditing() {
        Optional<User> user = userRepository.findById(1);
        user.ifPresent(u -> u.setBirthDate(u.getBirthDate().plusYears(1)));
        userRepository.flush();
    }

    @Test
    void checkCustomFilterUserRepository() {
        UserFilter userFilter = new UserFilter("Ivan", "Ivanov", null);
        List<User> personalInfoList = userRepository.findAllByFilter(userFilter);
        System.out.println(personalInfoList);
    }

    @Test
    void checkProjections() {
        List<PersonalInfo2> personalInfoList = userRepository.findAllByCompanyId(1L);
        System.out.println(personalInfoList);
    }

    @Test
    void checkPageable() {
        Sort id = Sort.by("id").and(Sort.by("firstname"));

        System.out.println(id);
        System.out.println(id.isUnsorted());

        PageRequest pageable = PageRequest.of(1, 2, id);
        Slice<User> users = userRepository.findAllBy(pageable);
        users.forEach(System.out::println);
    }

    @Test
    void checkSlice() {
        PageRequest pageable = PageRequest.of(0, 2, Sort.by("id"));
        Slice<User> slice = userRepository.findAllBy(pageable);
        System.out.println(slice.toString());

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
