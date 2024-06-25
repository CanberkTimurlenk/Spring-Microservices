package com.microservices.notificationservice.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface EmailStrategy {

    public void send() throws JsonProcessingException, UnirestException;
}
