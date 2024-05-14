package com.robotdreams.discountservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Document
public class Coupon implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Version
    private Long version;

    @Id
    private Long id;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    private long productId;
    private String description;
    private BigDecimal Amount;
    private String couponCode;

}
