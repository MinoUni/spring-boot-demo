package com.miniuni.config;

import com.miniuni.entity.User;
import com.miniuni.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            List<User> users = List.of(
                    new User("Amber", "Stark", LocalDate.of(1999, SEPTEMBER, 19)),
                    new User("Lexi", "Woods", LocalDate.of(2020, MAY, 25)),
                    new User("Mary", "Jones", LocalDate.of(1987, MARCH, 15)),
                    new User("John", "Bricks", null)
            );
            userRepository.saveAll(users);
        };
    }
}
