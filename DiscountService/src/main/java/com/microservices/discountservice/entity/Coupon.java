package com.microservices.discountservice.entity;

import com.microservices.discountservice.exceptionHandling.BusinessException;
import com.microservices.discountservice.exceptionHandling.InvalidAmountException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public abstract class Coupon {

    @Version
    private Long version;

    @Id
    private Long id;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    private String description;
    private BigDecimal amount;
    private String couponCode;
    private Date expirationDate;

    public void setAmount(BigDecimal amount) throws InvalidAmountException {

        if (amount.compareTo(new BigDecimal("100")) > 0
                || amount.compareTo(new BigDecimal("0")) < 0) {

            throw new InvalidAmountException("Invalid amount was entered. The amount must be interval in % 0-100");
        }
        this.amount = amount;
    }

    public void setExpirationDate(Date date)
    {
        if(date.before(new Date()))
            throw new BusinessException("The expiration date must be later than present");

        this.expirationDate = date;
    }
}
