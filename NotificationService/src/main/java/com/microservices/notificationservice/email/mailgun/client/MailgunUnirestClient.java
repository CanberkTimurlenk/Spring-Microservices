package com.microservices.notificationservice.email.mailgun.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.microservices.notificationservice.configuration.mailgun.MailgunProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailgunUnirestClient {

    private final MailgunProperties mailgunProperties;

    public JsonNode sendMessage(String from, String to, String subject, String body) throws UnirestException {

        String fullUrl = String.format("%s%s%s%s",
                mailgunProperties.getUrl(),
                mailgunProperties.getVersion(),
                mailgunProperties.getDomain(),
                mailgunProperties.getMessagesPath());

        HttpResponse<JsonNode> request = Unirest.post(fullUrl)
                .basicAuth(mailgunProperties.getUsername(), mailgunProperties.getPassword())
                .queryString("from", from)
                .queryString("to", to)
                .queryString("subject", subject)
                .queryString("text", body)
                .asJson();

        return request.getBody();
    }
}
