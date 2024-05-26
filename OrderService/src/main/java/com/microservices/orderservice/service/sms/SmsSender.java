package com.microservices.orderservice.service.sms;

import com.microservices.orderservice.dto.response.external.UserInfoResponseDto;
import com.microservices.orderservice.entity.Order;

public class SmsSender {
    private final SmsStrategy smsStrategy;

    public SmsSender(SmsStrategy smsStrategy) {
        this.smsStrategy = smsStrategy;
    }


    public void sendOrderCreatedSms(Order order, UserInfoResponseDto user) {
        smsStrategy.sendOrderCreatedSms(order, user);
    }


}