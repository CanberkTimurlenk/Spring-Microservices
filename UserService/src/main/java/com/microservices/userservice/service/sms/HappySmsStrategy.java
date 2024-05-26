package com.microservices.userservice.service.sms;

import com.microservices.userservice.customFunctions.UserUpdatedSmsFunction;
import com.microservices.userservice.entity.User;
import org.springframework.scheduling.annotation.Async;


public class HappySmsStrategy implements SmsStrategy {

    @Async
    public void sendUserUpdatedSms(User user) {

        UserUpdatedSmsFunction smsReplaceFunction = (template, firstname, lastname) ->
                template.replace("FIRSTNAME", firstname).replace("LASTNAME", lastname);

        String firstname = user.getFirstname();
        String lastname = user.getLastname();

        String smsBody = "Premium kullanıcımız, Sevgili FIRSTNAME LASTNAME kullanıcı bilgilerin güncellenmiştir.";

        smsReplaceFunction.replace(smsBody, firstname, lastname);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
