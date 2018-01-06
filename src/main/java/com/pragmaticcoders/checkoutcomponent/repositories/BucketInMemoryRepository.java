package com.pragmaticcoders.checkoutcomponent.repositories;

import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Repository
public class BucketInMemoryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketInMemoryRepository.class);

    private Set<TransactionItem> bucketRepository;

    public BucketInMemoryRepository() {
        bucketRepository = new HashSet<>();
    }

    public Set<TransactionItem> getItems() {
        LOGGER.debug("List items from bucket");
        return bucketRepository;
    }

    public void addItem(TransactionItem transactionItem) {
        LOGGER.debug("Add item to bucket: %s", transactionItem);
        bucketRepository.add(transactionItem);
    }

    public void clearBucket() {
        bucketRepository = new HashSet<>();
    }

    public BigDecimal getTotalAmount() {
        LOGGER.debug("Get total amount of bucket");
        return bucketRepository.stream().map(TransactionItem::getPrice).reduce((price1, price2) -> price1.add(price2)).get();
    }

}

