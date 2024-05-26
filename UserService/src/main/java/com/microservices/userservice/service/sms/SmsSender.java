package com.microservices.userservice.service.sms;

import com.microservices.userservice.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SmsSender {

    private final SmsStrategy smsStrategy;

    public void sendUserUpdatedSms(User user) {
        smsStrategy.sendUserUpdatedSms(user);
    }

}