package com.microservices.notificationservice.email;

import com.microservices.notificationservice.dto.EmailRequestDto;

public interface EmailStrategy {

    void send(EmailRequestDto emailRequestDto);
}
