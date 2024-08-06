package com.microservices.notificationservice.email.smtp;

import com.microservices.notificationservice.dto.EmailRequestDto;
import com.microservices.notificationservice.email.EmailStrategy;
import org.springframework.stereotype.Component;

@Component("SmtpEmailStrategy")
public class SmtpEmailStrategy implements EmailStrategy {

    @Override
    public void send(EmailRequestDto emailRequestDto) {
        // TODO: Implement the method body

    }

}
