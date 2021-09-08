package com.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @EmbeddedId
    private OrderPK orderPK;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product productId;

    private Long amount;
    private Long unitCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_phone")
    private Customer customerPhone;

    private OffsetDateTime requestDate;
    private OffsetDateTime purchaseDate;
    private String noteRequest;
    private String notePurchase;

    public Order(Product productId, Long amount, Long unitCost,
                 Customer customerPhone, String noteRequest) {
        this.orderPK = new OrderPK();
        this.orderPK.setProductId(productId.getId());
        this.productId = productId;
        this.amount = amount;
        this.unitCost = unitCost;
        this.customerPhone = customerPhone;
        this.requestDate = OffsetDateTime.now();
        this.purchaseDate = null;
        this.noteRequest = noteRequest;
        this.notePurchase = null;
    }

    public Order(Long orderId, Product productId, Long amount, Long unitCost,
                 Customer customerPhone, String noteRequest) {
        this.orderPK = new OrderPK(orderId, productId.getId());
        this.productId = productId;
        this.amount = amount;
        this.unitCost = unitCost;
        this.customerPhone = customerPhone;
        this.requestDate = OffsetDateTime.now();
        this.purchaseDate = null;
        this.noteRequest = noteRequest;
        this.notePurchase = null;
    }

    public String getProductsName() {
        return getProductId().getName();
    }

    public String getStringCustomerPhone() {
        return getCustomerPhone().getPhone();
    }
}
