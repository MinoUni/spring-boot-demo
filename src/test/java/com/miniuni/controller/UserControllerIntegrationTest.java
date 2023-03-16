package com.miniuni.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserShouldReturnUserDtoWithResponse200() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("Amber"))
                .andExpect(jsonPath("lastName").value("Stark"))
                .andExpect(jsonPath("age").value("23"));
    }

    @Test
    void getUserShouldReturnExceptionWithResponse404() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/{id}", 99))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").value("User with this id not exist: 99"))
                .andExpect(jsonPath("httpStatus").value("NOT_FOUND"));
    }
}