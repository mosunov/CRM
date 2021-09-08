package com.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    private String name;
    private Long amountSold;
    private Long amountWeOrdered;
    private Long proceeds;
    private Long purchaseCost;


    public Product(String name, Long amountSold,
                   Long amountWeOrdered, Long proceeds,
                   Long purchaseCost) {
        this.name = name;
        this.amountSold = amountSold;
        this.amountWeOrdered = amountWeOrdered;
        this.proceeds = proceeds;
        this.purchaseCost = purchaseCost;
    }

    public Long getAmountLeft() {
        return getAmountWeOrdered() - getAmountSold();
    }

    public Long getProfit() {
        return this.proceeds - this.purchaseCost;
    }
}
