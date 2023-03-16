package com.miniuni.service.impl;

import com.miniuni.constants.ErrorMessage;
import com.miniuni.constants.LogMessage;
import com.miniuni.dto.UserDto;
import com.miniuni.entity.User;
import com.miniuni.exception.UserNotFoundException;
import com.miniuni.repository.UserRepository;
import com.miniuni.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Get {@link User} by his id from db and map into {@link UserDto} DTO
     *
     * @param id user
     * @return {@link UserDto} DTO
     */
    @Override
    public UserDto getUserById(Long id) {
        log.info(LogMessage.IN_FIND_BY_ID, id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND_BY_ID + id));
        return new UserDto(user.getFirstName(), user.getLastName(), calcAge(user));
    }

    /**
     * Calculate user's age base on his birthdate
     *
     * @param user {@link User}
     * @return user age
     */
    private Integer calcAge(User user) {
        return Period.between(user.getBirthdate(), LocalDate.now()).getYears();
    }
}
