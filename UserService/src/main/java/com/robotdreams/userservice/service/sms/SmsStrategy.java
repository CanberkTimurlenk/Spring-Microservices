package com.robotdreams.userservice.service.sms;

import com.robotdreams.userservice.entity.User;

public interface SmsStrategy {

    void sendUserUpdatedSms(User user);

}
