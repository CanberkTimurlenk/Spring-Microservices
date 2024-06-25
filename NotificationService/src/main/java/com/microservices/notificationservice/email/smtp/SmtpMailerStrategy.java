package com.microservices.notificationservice.email.smtp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.microservices.notificationservice.email.EmailStrategy;

public class SmtpMailerStrategy implements EmailStrategy {
    @Override
    public void send() throws JsonProcessingException, UnirestException {
        // TODO: Implement the method body

    }
}
