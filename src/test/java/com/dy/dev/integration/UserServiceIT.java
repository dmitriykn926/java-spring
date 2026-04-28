package com.dy.dev.integration;

import com.dy.dev.dto.UserCreateEditDto;
import com.dy.dev.dto.UserReadDto;
import com.dy.dev.dto.entity.Role;
import com.dy.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


//TODO As there are two IT test, the ApplicationContext being raised two times: 1 time for CompanyServiceIT, 2 for UserServiceIT
// to avoid that usually created a new TestConfigurationRunner which be specified inside @SpringBootTest annotation
//@SpringBootTest(classes = TestConfigurationRunner.class)
//@IT //TODO VERY IMPORTANT. In case of there are some changes in different classes (for example: no @ActiveProfiles), then a new ApplicationContext will be raised
// A good practise to use the same annotation for all IT tests, then only one ApplicationContext be raised for all IT test classes
@RequiredArgsConstructor
public class UserServiceIT extends IntegrationBaseTest {

    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> users = userService.findAll();
        assertThat(users).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> userReadDto = userService.findById(1);
        assertTrue(userReadDto.isPresent());
        userReadDto.ifPresent(user -> assertEquals("ivan@gmail.com", user.username()));
    }

    @Test
    void create() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.USER,
                1
        );
        UserReadDto userReadDto = userService.create(userCreateEditDto);
        assertNotNull(userReadDto);
    }

    @Test
    void update() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.USER,
                1
        );
        Optional<UserReadDto> update = userService.update(1, userCreateEditDto);
        assertTrue(update.isPresent());
    }

    @Test
    void delete() {
        assertFalse(userService.delete(100));
        assertTrue(userService.delete(1));
    }
}
