package com.miniuni.config;

import com.miniuni.entity.User;
import com.miniuni.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.List;

@Configuration
public class DataBaseFill {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            List<User> users = List.of(
                    new User("Amber", "Stark", Date.valueOf("1999-09-19")),
                    new User("Lexi", "Woods", Date.valueOf("2020-05-25")),
                    new User("Mary", "Jones", Date.valueOf("1987-03-15")),
                    new User("John", "Bricks", Date.valueOf("1967-02-22"))
            );
            userRepository.saveAll(users);
        };
    }
}
