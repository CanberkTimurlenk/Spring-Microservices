package com.microservices.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User extends BaseEntity implements Serializable {

    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean premium;
    private String userImageKey;
}
