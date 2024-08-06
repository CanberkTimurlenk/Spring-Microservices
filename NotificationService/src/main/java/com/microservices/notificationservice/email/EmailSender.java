package com.microservices.notificationservice.email;

import com.microservices.notificationservice.dto.EmailRequestDto;
import com.microservices.notificationservice.email.mailgun.MailgunEmailStrategy;
import com.microservices.notificationservice.email.smtp.SmtpEmailStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailSender {

    private final Map<String, EmailStrategy> emailStrategyMap;

    public void sendEmail(EmailRequestDto emailRequestDto) {

        if (emailRequestDto.body().length() > 100) {
            emailStrategyMap.get(
                            SmtpEmailStrategy.class.getName())
                    .send(emailRequestDto);

            return;
        }
        emailStrategyMap.get(
                        MailgunEmailStrategy.class.getName())
                .send(emailRequestDto);
    }
}