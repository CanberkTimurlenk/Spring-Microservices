package com.microservices.notificationservice.email.smtp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.microservices.notificationservice.dto.EmailRequestDto;
import com.microservices.notificationservice.email.EmailStrategy;
import org.springframework.stereotype.Component;

@Component("SmtpMailer")
public class SmtpMailerStrategy implements EmailStrategy {
    @Override
    public void send(EmailRequestDto emailRequest) {
        // TODO: Implement the method body

    }
}
