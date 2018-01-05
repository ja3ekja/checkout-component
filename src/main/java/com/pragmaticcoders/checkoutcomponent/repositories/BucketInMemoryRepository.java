package com.pragmaticcoders.checkoutcomponent.repositories;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SessionScope
@Repository
public class BucketInMemoryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketInMemoryRepository.class);

    private List<Item> bucketRepository;

    public BucketInMemoryRepository() {
        bucketRepository = new ArrayList<>();
    }

    public List<Item> getItems() {
        return bucketRepository;
    }

    public void addItem(Item item) {
        bucketRepository.add(item);
    }

    public void clearBucket() {
        bucketRepository = new ArrayList<>();
    }

    public BigDecimal getTotalAmount() {
        return bucketRepository.stream().map(Item::getPrice).reduce((price1, price2) -> price1.add(price2)).get();
    }

}

