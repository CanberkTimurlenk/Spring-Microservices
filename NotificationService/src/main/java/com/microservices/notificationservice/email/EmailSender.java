package com.microservices.notificationservice.email;

import com.microservices.notificationservice.constant.Email;
import com.microservices.notificationservice.dto.EmailRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final Map<String, EmailStrategy> emailStrategyMap;

    public void sendEmail(EmailRequestDto emailRequest) {

        // If body length of the mail is greater than 100, then use smtp to send to the email
        // otherwise use Mailgun

        if (emailRequest.body().length() > 100) {
            emailStrategyMap.get(Email.SMTP_EMAIL_STRATEGY).send(emailRequest);
            return;
        }

        emailStrategyMap.get(Email.MAILGUN_EMAIL_STRATEGY).send(emailRequest);
    }
}
