package com.miniuni.controller;

import com.miniuni.dto.UserDto;
import com.miniuni.exception.UserNotFoundException;
import com.miniuni.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void getUserShouldReturnUserDtoWithResponse200() throws Exception {
        Long id = 1L;
        UserDto user = new UserDto("Test-first", "Test-last", 10);
        given(userService.getUserById(id)).willReturn(user);
        this.mockMvc.perform(get("/api/v1/users/{id}", id)
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("Test-first"))
                .andExpect(jsonPath("lastName").value("Test-last"))
                .andExpect(jsonPath("age").value("10"));
    }

    @Test
    public void getUserShouldReturnUserNotFoundExceptionWithResponse404() throws Exception {
        Long id = 1L;
        given(userService.getUserById(id))
                .willThrow(new UserNotFoundException("User with this id not exist: " + id));
        this.mockMvc.perform(get("/api/v1/users/{id}", id)
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").value("User with this id not exist: " + id))
                .andExpect(jsonPath("httpStatus").value("NOT_FOUND"));
    }
}