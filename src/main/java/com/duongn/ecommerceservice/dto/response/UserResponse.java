package com.duongn.ecommerceservice.dto.response;

import com.duongn.ecommerceservice.enums.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long id;

    String username;

    String email;

    String fullName;

    String phone;

    String address;

    UserRole role;
}
