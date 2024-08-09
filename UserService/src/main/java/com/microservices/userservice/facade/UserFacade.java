package com.microservices.userservice.facade;

import com.microservices.userservice.dto.UserRequestDto;
import com.microservices.userservice.s3.MultipartFileStrategy;
import com.microservices.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.common.sns.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserService userService;
    private final StorageService storageService;

    public boolean save(UserRequestDto userRequestDto, MultipartFile multipartFile) {
        var result = userService.save(userRequestDto);

        if (!result)
            return false;

        storageService.save(new MultipartFileStrategy(multipartFile), userRequestDto.userImageKey());
        return true;
    }
}