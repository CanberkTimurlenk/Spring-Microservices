package com.robotdreams.userservice.controller;

import com.robotdreams.userservice.dto.UserRequestDto;
import com.robotdreams.userservice.dto.UserResponseDto;
import com.robotdreams.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
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
