package com.robotdreams.userservice.utils;

import com.robotdreams.userservice.dto.UserRequestDto;
import com.robotdreams.userservice.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
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