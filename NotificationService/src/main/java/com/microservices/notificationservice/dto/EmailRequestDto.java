package com.microservices.notificationservice.dto;

public record EmailRequestDto(String to, String subject, String body) {
}
