package com.microservices.notificationservice.email;

import com.microservices.notificationservice.dto.EmailRequestDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailSender {
    private final EmailStrategy emailStrategy;

    public void sendEmail(EmailRequestDto emailRequestDto) {
        emailStrategy.send(emailRequestDto);
    }
}
