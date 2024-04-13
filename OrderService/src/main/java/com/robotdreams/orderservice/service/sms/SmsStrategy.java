package com.robotdreams.orderservice.service.sms;

import com.robotdreams.orderservice.dto.response.external.UserInfoResponseDto;
import com.robotdreams.orderservice.entity.Order;

public interface SmsStrategy {
    public void sendOrderCreatedSms(Order order, UserInfoResponseDto user);

}
