package com.pragmaticcoders.checkoutcomponent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    @OneToOne(cascade = CascadeType.ALL)
    private Item item;

    public Promotion(Item item, Integer quantity, BigDecimal price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }
}
