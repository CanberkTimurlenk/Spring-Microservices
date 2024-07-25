package com.microservices.notificationservice.email.mailgun;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.microservices.notificationservice.dto.EmailRequestDto;
import com.microservices.notificationservice.email.EmailStrategy;

import com.microservices.notificationservice.email.mailgun.client.MailgunUnirestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("MailgunMailer")
@RequiredArgsConstructor
public class MailgunMailerStrategy implements EmailStrategy {

    private final MailgunUnirestClient mailgunUnirestClient;

    @Override
    public void send(EmailRequestDto emailRequest) {

        emailRequest = new EmailRequestDto("TO", "SUBJECT", "BODY");
        try {
            mailgunUnirestClient.sendMessage("FROM", emailRequest.to(), emailRequest.subject(), emailRequest.body());
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}