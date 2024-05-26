package com.microservices.userservice.service.sms;

import com.microservices.userservice.entity.User;

public interface SmsStrategy {

    void sendUserUpdatedSms(User user);

}
