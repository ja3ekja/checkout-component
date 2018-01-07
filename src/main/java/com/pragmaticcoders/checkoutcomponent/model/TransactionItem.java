package com.pragmaticcoders.checkoutcomponent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receipt_item")
public class TransactionItem {

    @Id
    private UUID uuid;
    private Long itemId;
    private String name;
    private BigDecimal price;

    public TransactionItem(Long itemId, String name, BigDecimal price) {
        this.uuid = UUID.randomUUID();
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }
}
