package com.microservices.userservice.utils;

import com.microservices.userservice.dto.UserRequestDto;
import com.microservices.userservice.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilTest {

    private final UserService userService;


    @PostConstruct
    public void saveTestData() {

        // create sample user data
        UserRequestDto userRequestDto = new UserRequestDto(
                "John",
                "Doe",
                "john.doe@example.com",
                "+1234567890",
                "123 Main Street, Cityville, ST 12345",
                true
        );

        // save sample user date
        userService.save(userRequestDto);


    }
}