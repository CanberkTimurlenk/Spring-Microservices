package com.microservices.shipmentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;


@Table(name = "Shipments")
@Entity
@Getter
@Setter
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long shipmentId;
    private long orderId;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    @OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductShipment> productShipments;

    // TODO: Weight and total weight could be added in the future

}
