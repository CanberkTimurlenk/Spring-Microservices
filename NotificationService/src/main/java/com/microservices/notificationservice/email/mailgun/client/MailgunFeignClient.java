package com.microservices.notificationservice.email.mailgun.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "mailgunClient",
        url = "${mailgun.url}",
        configuration = MailgunFeignClient.class)
public interface MailgunFeignClient {

    @PostMapping(value = "/v3/domain/messages", consumes = "application/x-www-form-urlencoded")
    JsonNode sendMessage(@PathVariable("domain") String domain,
                         @RequestParam("from") String from,
                         @RequestParam("to") String to,
                         @RequestParam("subject") String subject,
                         @RequestParam("text") String text);

}