package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
import com.pragmaticcoders.checkoutcomponent.model.Item;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BucketItemService {

    public TransactionItem parse(Item item) {
        TransactionItem transactionItem = new TransactionItem();
        UUID uuid = UUID.randomUUID();
        transactionItem.setUuid(uuid);
        transactionItem.setItemId(item.getId());
        transactionItem.setName(item.getName());
        transactionItem.setPrice(item.getPrice());
        return transactionItem;
    }
}
