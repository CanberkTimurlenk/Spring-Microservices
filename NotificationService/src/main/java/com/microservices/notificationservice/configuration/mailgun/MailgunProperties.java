package com.microservices.notificationservice.configuration.mailgun;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "mailgun")
@Getter
public class MailgunProperties {
    private final String url;
    private final String domain;
    private final String version;
    private final String messagesPath;
    private final String username;
    private final String password;

    @ConstructorBinding
    public MailgunProperties(String url, String domain, String version, String messagesPath, String username, String password) {
        this.url = url;
        this.domain = domain;
        this.version = version;
        this.messagesPath = messagesPath;
        this.username = username;
        this.password = password;
    }
}