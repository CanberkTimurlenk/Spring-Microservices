package com.robotdreams.userservice.service.sms;

import com.robotdreams.userservice.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SmsSender {

    private final SmsStrategy smsStrategy;

    public void sendUserUpdatedSms(User user) {
        smsStrategy.sendUserUpdatedSms(user);
    }

}