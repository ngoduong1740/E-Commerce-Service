package com.duongn.ecommerceservice.mapper;

import com.duongn.ecommerceservice.dto.request.CreateUserRequest;
import com.duongn.ecommerceservice.dto.request.UpdateUserRequest;
import com.duongn.ecommerceservice.dto.response.UserResponse;
import com.duongn.ecommerceservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(CreateUserRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UpdateUserRequest request);
}
