package com.miniuni.controller;

import com.miniuni.dto.UserDto;
import com.miniuni.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     *  Method to get user by his id
     *
     * @param id user id
     * @return response {@link UserDto} object
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@NotNull @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }
}
