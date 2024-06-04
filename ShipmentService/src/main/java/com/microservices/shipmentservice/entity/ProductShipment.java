package com.microservices.shipmentservice.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ProductShipments")
@Getter
@Setter
public class ProductShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "shipmentId")
    private Shipment shipment;

    private long productId;
    private long quantity;

}
