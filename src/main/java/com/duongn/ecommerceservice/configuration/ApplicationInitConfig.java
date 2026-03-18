package com.duongn.ecommerceservice.configuration;

import com.duongn.ecommerceservice.entity.Cart;
import com.duongn.ecommerceservice.entity.User;
import com.duongn.ecommerceservice.enums.UserRole;
import com.duongn.ecommerceservice.repository.CartRepository;
import com.duongn.ecommerceservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    static final String ADMIN_USER_NAME = "admin";
    static final String ADMIN_PASSWORD = "admin";
    static final String ADMIN_EMAIL = "admin@gmail.com";
    static final String ADMIN_FULL_NAME = "System Admin";

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, CartRepository cartRepository) {
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                User user = User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .email(ADMIN_EMAIL)
                        .fullName(ADMIN_FULL_NAME)
                        .phone("0000000000")
                        .address("System")
                        .role(UserRole.ADMIN)
                        .build();

                user = userRepository.save(user);

                Cart cart = Cart.builder()
                        .user(user)
                        .items(new ArrayList<>())
                        .build();

                cartRepository.save(cart);
            }
        };
    }
}
