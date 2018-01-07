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

    public Promotion(BigDecimal price, Integer quantity, Item item) {
        this.price = price;
        this.quantity = quantity;
        this.item = item;
    }
}
