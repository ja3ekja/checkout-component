package com.pragmaticcoders.checkoutcomponent.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Item {

    private long Id;
    private String name;
    private BigDecimal price;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && getName().equals(item.getName());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        return result;
    }
}
