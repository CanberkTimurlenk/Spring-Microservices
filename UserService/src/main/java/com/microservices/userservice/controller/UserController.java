package com.microservices.userservice.controller;

import com.microservices.userservice.dto.UserCredentials;
import com.microservices.userservice.dto.UserRequestDto;
import com.microservices.userservice.dto.UserResponseDto;
import com.microservices.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserRequestDto userRequestDto) {

        return userService.save(userRequestDto)
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

        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable long userId) {

        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/userValidity")
    public ResponseEntity<Boolean> checkIfUserIsValid(long userId) {

        if (userService.checkIfUserIsValid(userId))
            return ResponseEntity.ok().build();

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/findByCredentials")
    public ResponseEntity<UserResponseDto> findByCredentials(@RequestBody UserCredentials credentials) {

        return userService.findByCredentials(credentials)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
