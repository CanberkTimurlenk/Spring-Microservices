package com.microservices.orderservice.service.sms;

import com.microservices.orderservice.dto.response.external.UserInfoResponseDto;
import com.microservices.orderservice.entity.Order;

public interface SmsStrategy {
    public void sendOrderCreatedSms(Order order, UserInfoResponseDto user);

}
