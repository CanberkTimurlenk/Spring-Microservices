package com.robotdreams.userservice.service.mapper;

import com.robotdreams.userservice.dto.UserRequestDto;
import com.robotdreams.userservice.dto.UserResponseDto;
import com.robotdreams.userservice.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    User userRequestDtoToUser(UserRequestDto dto);

    User updateUser(@MappingTarget User user, UserRequestDto dto);

    UserResponseDto userToUserResponseDto(User user);
}
