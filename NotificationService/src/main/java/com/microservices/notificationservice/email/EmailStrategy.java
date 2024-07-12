package com.microservices.notificationservice.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.microservices.notificationservice.dto.EmailRequestDto;

public interface EmailStrategy {

    public void send(EmailRequestDto emailRequestDto);
}
