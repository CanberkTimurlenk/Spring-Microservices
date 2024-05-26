package com.microservices.discountservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class DatabaseSequence {
    @Id
    private String id;

    private long seq;

    public DatabaseSequence() {
    }

}