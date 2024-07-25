package com.microservices.notificationservice.controller;

import com.microservices.notificationservice.dto.EmailRequestDto;
import com.microservices.notificationservice.email.mailgun.client.MailgunClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {

    @PostMapping
    public void send(EmailRequestDto emailRequestDto) {

    }
}
