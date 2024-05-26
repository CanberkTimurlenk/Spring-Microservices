package com.microservices.orderservice.service.sms;

import com.microservices.orderservice.customFunctions.OrderCreatedSmsFunction;
import com.microservices.orderservice.dto.response.external.UserInfoResponseDto;
import com.microservices.orderservice.entity.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HappySmsStrategy implements SmsStrategy {

    @Async
    public void sendOrderCreatedSms(Order order, UserInfoResponseDto user) {

        OrderCreatedSmsFunction smsReplaceFunction = (template, firstname, email, orderNumber) ->
                template.replace("NAME", firstname).replace("EMAIL", email).replace("ORDERNUMBER", orderNumber);

        var orderNumber = order.getOrderNumber();
        var name = user.firstname();
        var email = user.email();

        // mocking
        var phoneNumber = user.phoneNumber();

        String smsBody = "Premium kullanıcımız, Sevgili NAME siparişini aldık, Siparişinin numarası: ORDERNUMBER . Siparişinin detaylarına EMAIL adresine gönderdiğimiz mailden ulaşabilirsin.";

        smsReplaceFunction.replace(smsBody, name, email, orderNumber);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
