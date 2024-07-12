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

    private final String DOMAIN = "sandbox6634e812984f4f4184f14904751f7b99.mailgun.org";
    private final String FROM = "testdeneme@mail.com";

    private final MailgunClient mailgunClient;

    @GetMapping
    public void test() {
        var mailDto = new EmailRequestDto("deadlycoldfire@gmail.com", "spring-test", "body example");
        mailgunClient.sendMessage(DOMAIN, FROM, mailDto.to(), mailDto.subject(), mailDto.body());
    }

    @PostMapping
    public void send(EmailRequestDto emailRequestDto) {

    }
}
