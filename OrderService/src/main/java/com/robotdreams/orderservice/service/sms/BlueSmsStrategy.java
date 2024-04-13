package com.robotdreams.orderservice.service.sms;

import com.robotdreams.orderservice.customFunctions.OrderCreatedSmsFunction;
import com.robotdreams.orderservice.dto.response.external.UserInfoResponseDto;
import com.robotdreams.orderservice.entity.Order;
import org.springframework.scheduling.annotation.Async;

public class BlueSmsStrategy implements SmsStrategy {

    @Async
    public void sendOrderCreatedSms(Order order, UserInfoResponseDto user) {

        OrderCreatedSmsFunction smsReplaceFunction = (template, firstname, email, orderNumber) ->
                template.replace("NAME", firstname).replace("EMAIL", email).replace("ORDERNUMBER", orderNumber);

        var orderNumber = order.getOrderNumber();
        var name = user.firstname();
        var email = user.email();

        // mocking
        var phoneNumber = user.phoneNumber();

        String smsBody = "Sevgili NAME siparişini aldık, Siparişinin numarası: ORDERNUMBER . Siparişinin detaylarına EMAIL adresine gönderdiğimiz mailden ulaşabilirsin.";

        smsReplaceFunction.replace(smsBody, name, email, orderNumber);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
