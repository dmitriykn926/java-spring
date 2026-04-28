package com.dy.dev.integration;

import com.dy.dev.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc // TODO. To simulate Http requests to Controller
@RequiredArgsConstructor
public class UserRestControllerTest extends IntegrationBaseTest {

    private final MockMvc mockMvc;

    @SneakyThrows
    @Test
    void findAll() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");
        ResultActions perform = mockMvc.perform(requestBuilder);

        perform.andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attribute("users", hasSize(5)));
    }

    @Test
    @SneakyThrows
    void create() {
        MockHttpServletRequestBuilder post = post("/users")
                .param(UserCreateEditDto.Fields.username, "test@gmail.com")
                .param(UserCreateEditDto.Fields.firstname, "Test")
                .param(UserCreateEditDto.Fields.lastname, "Test")
                .param(UserCreateEditDto.Fields.role, "ADMIN")
                .param(UserCreateEditDto.Fields.companyId, "1")
                .param(UserCreateEditDto.Fields.birthDate, "2000-01-01");

        ResultActions perform = mockMvc.perform(post)
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}"));
    }
}
