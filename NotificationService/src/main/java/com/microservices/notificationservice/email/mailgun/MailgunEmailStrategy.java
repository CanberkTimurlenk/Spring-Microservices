package com.microservices.notificationservice.email.mailgun;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.microservices.notificationservice.dto.EmailRequestDto;
import com.microservices.notificationservice.email.EmailStrategy;

import com.microservices.notificationservice.email.mailgun.client.MailgunUnirestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class MailgunEmailStrategy implements EmailStrategy {

    private final MailgunUnirestClient mailgunUnirestClient;

    @Override
    public void send(EmailRequestDto emailRequestDto) {

        emailRequestDto = new EmailRequestDto("TO", "SUBJECT", "BODY");
        try {
            mailgunUnirestClient.sendMessage("FROM", emailRequestDto.to(), emailRequestDto.subject(), emailRequestDto.body());
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}