package com.pragmaticcoders.checkoutcomponent.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TransactionItem> transactionItems;
    private BigDecimal totalPrice;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt)) return false;
        if (!super.equals(o)) return false;

        Receipt receipt = (Receipt) o;

        if (!getId().equals(receipt.getId())) return false;
        return getName().equals(receipt.getName());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}