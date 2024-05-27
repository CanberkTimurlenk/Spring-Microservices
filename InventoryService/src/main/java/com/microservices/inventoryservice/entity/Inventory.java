package com.microservices.inventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "Inventories", uniqueConstraints = {@UniqueConstraint(columnNames = "productId")})
@Entity
@Getter
@Setter
public class Inventory {

    @Id
    private long id;
    private long productId;
    private long inventoryAmount;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;
}