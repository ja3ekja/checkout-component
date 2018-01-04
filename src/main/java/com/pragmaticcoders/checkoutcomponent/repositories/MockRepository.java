package com.pragmaticcoders.checkoutcomponent.repositories;

import com.pragmaticcoders.checkoutcomponent.controllers.BucketScanner;
import com.pragmaticcoders.checkoutcomponent.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository()
public class MockRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockRepository.class);

    private static Map<Long, Item> mockRepository;

    {MockRepository.setup();}

    private static void setup() {
        Item item1 = new Item(1L, "RAM memory", new BigDecimal(100.00), 20);
        Item item2 = new Item(2L, "Hard drive", new BigDecimal(200.00), 300);
        Item item3 = new Item(3L, "Processor", new BigDecimal(70.00), 50);
        mockRepository = new HashMap<>();
        mockRepository.put(item1.getId(), item1);
        mockRepository.put(item2.getId(), item2);
        mockRepository.put(item3.getId(), item3);
    }

    public Item getItem(long id) {
        LOGGER.debug("Fetch item with id: %s", id);
        return mockRepository.get(id);
    }

    public BigDecimal getPrice(long id) {
        LOGGER.debug("Fetch price of item with id: %s", id);
        return mockRepository.get(id).getPrice();
    }

    public int getQuantity(long id) {
        LOGGER.debug("Fetch quantity of item with id: %s", id);
        return mockRepository.get(id).getQuantity();
    }
}
