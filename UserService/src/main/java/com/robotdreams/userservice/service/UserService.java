package com.robotdreams.userservice.service;

import com.robotdreams.userservice.dto.UserRequestDto;
import com.robotdreams.userservice.dto.UserResponseDto;
import com.robotdreams.userservice.entity.User;
import com.robotdreams.userservice.repository.UserRepository;
import com.robotdreams.userservice.service.mapper.UserMapper;
import com.robotdreams.userservice.service.sms.BlueSmsStrategy;
import com.robotdreams.userservice.service.sms.HappySmsStrategy;
import com.robotdreams.userservice.service.sms.SmsSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<UserResponseDto> findById(long id) {

        Optional<User> user = userRepository.findById(id);

        return user.map(value -> Optional.of(userMapper.userToUserResponseDto(value)))
                .orElse(null);
    }

    public boolean save(UserRequestDto userRequestDto) {

        User user = userMapper.userRequestDtoToUser(userRequestDto);
        userRepository.save(user);

        return user.getId() > 0;
    }

    public boolean update(long id, UserRequestDto userRequestDto) {

        var user = userRepository.findById(id);

        // Not Found
        if (user.isEmpty())
            return false;

        User updatedUser = userMapper.updateUser(user.get(), userRequestDto);
        userRepository.save(updatedUser);

        // Send Sms
        if (updatedUser.isPremium())
            new SmsSender(new HappySmsStrategy()).sendUserUpdatedSms(user.get());

        else
            new SmsSender(new BlueSmsStrategy()).sendUserUpdatedSms(user.get());

        return true;
    }

    public List<UserResponseDto> findAll() {

        List<User> user = userRepository.findAll();

        return user.stream().map(userMapper::userToUserResponseDto).toList();
    }

}
