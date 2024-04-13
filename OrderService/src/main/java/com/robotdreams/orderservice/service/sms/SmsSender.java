package com.robotdreams.orderservice.service.sms;

import com.robotdreams.orderservice.dto.response.external.UserInfoResponseDto;
import com.robotdreams.orderservice.entity.Order;

public class SmsSender {
    private final SmsStrategy smsStrategy;

    public SmsSender(SmsStrategy smsStrategy) {
        this.smsStrategy = smsStrategy;
    }


    public void sendOrderCreatedSms(Order order, UserInfoResponseDto user) {
        smsStrategy.sendOrderCreatedSms(order, user);
    }


}