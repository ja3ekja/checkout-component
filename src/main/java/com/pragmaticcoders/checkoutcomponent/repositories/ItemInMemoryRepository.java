package com.pragmaticcoders.checkoutcomponent.repositories;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository()
@Scope(value = "singleton")
public class ItemInMemoryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemInMemoryRepository.class);

    private static Map<Long, Item> repository;

    {
        ItemInMemoryRepository.setup();
    }

    private static void setup() {
        Item item1 = new Item(1L, "RAM memory", new BigDecimal(100.00));
        Item item2 = new Item(2L, "Hard drive", new BigDecimal(200.00));
        Item item3 = new Item(3L, "Processor", new BigDecimal(70.00));
        repository = new HashMap<>();
        repository.put(item1.getId(), item1);
        repository.put(item2.getId(), item2);
        repository.put(item3.getId(), item3);
    }

    public Item getItem(long id) {
        LOGGER.debug("Fetch item with id: %s", id);
        return repository.get(id);
    }

    public BigDecimal getPrice(long id) {
        LOGGER.debug("Fetch price of item with id: %s", id);
        return repository.get(id).getPrice();
    }
}
