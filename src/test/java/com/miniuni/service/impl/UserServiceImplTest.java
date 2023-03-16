package com.miniuni.service.impl;

import com.miniuni.dto.UserDto;
import com.miniuni.entity.User;
import com.miniuni.exception.UserNotFoundException;
import com.miniuni.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static java.time.Month.SEPTEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void getUserByIdShouldReturnDto() throws Exception {
        Long id = 1L;
        User user = new User("fName", "lName", LocalDate.of(1999, SEPTEMBER, 20));

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDto dto = assertDoesNotThrow(() -> userService.getUserById(id));

        assertThat(dto).isNotNull();
        assertThat(dto.getFirstName()).isEqualTo("fName");
        assertThat(dto.getLastName()).isEqualTo("lName");
        assertThat(dto.getAge()).isEqualTo(23);

        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void getUserByIdShouldThrowUserNotFoundException() throws Exception {
        Long id = 5L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        UserNotFoundException excMessage = assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(id));

        assertThat(excMessage.getMessage()).isEqualTo("User with this id not exist: " + id);

        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void calcAgeShouldReturnZeroIfBirthdateIsMissed() throws Exception {
        Long id = 1L;
        User user = new User("fName", "lName", null);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserDto dto = assertDoesNotThrow(() -> userService.getUserById(id));

        assertThat(dto).isNotNull();
        assertThat(dto.getFirstName()).isEqualTo("fName");
        assertThat(dto.getLastName()).isEqualTo("lName");
        assertThat(dto.getAge()).isEqualTo(0);

        verify(userRepository, times(1)).findById(id);
    }
}