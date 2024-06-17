package com.microservices.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.userservice.dto.UserRequestDto;
import com.microservices.userservice.dto.UserResponseDto;
import com.microservices.userservice.facade.UserFacade;
import com.microservices.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> create(
            @RequestPart String userRequestDtoStr,
            @RequestPart MultipartFile multipartFile
            ) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        UserRequestDto userRequestDto = objectMapper.readValue(userRequestDtoStr, UserRequestDto.class);
        return userFacade.save(userRequestDto,multipartFile)
                ? new ResponseEntity<>("Successfully Created!", HttpStatus.CREATED)
                : new ResponseEntity<>("An Unexpected Error Occured!", HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> update(@PathVariable long userId, @RequestBody UserRequestDto userRequestDto) {

        return userService.update(userId, userRequestDto)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable long userId) {

        Optional<UserResponseDto> user = userService.findById(userId);

        return user.map(userResponseDto -> new ResponseEntity<>(userResponseDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/userValidity")
    public ResponseEntity<Boolean> checkIfUserIsValid(long userId) {

        if (userService.checkIfUserIsValid(userId))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

}
