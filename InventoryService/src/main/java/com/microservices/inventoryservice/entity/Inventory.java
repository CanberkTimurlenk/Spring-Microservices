package com.microservices.inventoryservice.entity;

import com.microservices.inventoryservice.exceptionhandling.InventoryException;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private long productId;
    private long stockAmount;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    public long reserveStock(long allocationAmount)
            throws InventoryException {

        if (stockAmount == 0 || stockAmount - allocationAmount < 0)
            throw new InventoryException(productId);

        stockAmount = stockAmount - allocationAmount;
        return stockAmount;
    }

    public void setStockAmount(long stockAmount) {

        if (stockAmount < 0)
            throw new RuntimeException("Stock amount must be greater than 0");

        this.stockAmount = stockAmount;
    }
}