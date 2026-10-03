package com.example.customerjwt.config;

import com.example.customerjwt.entity.Role;
import com.example.customerjwt.entity.User;
import com.example.customerjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner createAdmin(UserRepository repo, PasswordEncoder encoder,
                                  @Value("${app.admin.username}") String username,
                                  @Value("${app.admin.password}") String password) {
        return args -> {
            if (!repo.existsByUsername(username)) {
                repo.save(new User(username, encoder.encode(password), Role.ADMIN));
            }
        };
    }
}
