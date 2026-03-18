package com.duongn.ecommerceservice.dto.request;

import com.duongn.ecommerceservice.enums.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {

    String username;

    String email;

    String password;

    String fullName;

    String phone;

    String address;

    UserRole role;
}
