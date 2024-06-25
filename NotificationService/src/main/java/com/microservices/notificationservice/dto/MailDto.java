package com.microservices.notificationservice.dto;

public record MailDto(String to, String subject, String body) {
}
