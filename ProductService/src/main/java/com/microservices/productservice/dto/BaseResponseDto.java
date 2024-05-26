package com.microservices.productservice.dto;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public abstract class BaseResponseDto {

    private long id;
    private Date createDate;
    private Date updateDate;
    private String serverTime;

    public String getServerTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}