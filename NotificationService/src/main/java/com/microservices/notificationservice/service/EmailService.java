package com.microservices.notificationservice.service;

import com.microservices.notificationservice.dto.EmailRequestDto;
import com.microservices.notificationservice.email.EmailSender;
import com.microservices.notificationservice.email.EmailStrategy;
import com.microservices.notificationservice.email.mailgun.MailgunEmailStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {


    public void sendEmail(EmailRequestDto emailRequestDto) {

        new EmailSender(new MailgunEmailStrategy())


    }
}
