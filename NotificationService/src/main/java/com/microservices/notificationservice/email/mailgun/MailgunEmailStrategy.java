package com.microservices.notificationservice.email.mailgun;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.microservices.notificationservice.dto.MailDto;
import com.microservices.notificationservice.email.EmailStrategy;

import com.microservices.notificationservice.email.mailgun.client.MailgunUnirestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailgunEmailStrategy implements EmailStrategy {

    private final MailgunUnirestClient mailgunUnirestClient;

    @Override
    public void send() throws UnirestException {

        MailDto mailDto = new MailDto("TO", "SUBJECT", "BODY");
        mailgunUnirestClient.sendMessage("FROM", mailDto.to(), mailDto.subject(), mailDto.body());
    }
}