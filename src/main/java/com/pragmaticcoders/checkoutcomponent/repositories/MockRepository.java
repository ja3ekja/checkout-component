package com.pragmaticcoders.checkoutcomponent.repositories;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository()
public class MockRepository {

    private static Map<Long, Item> mockRepository;

    public static void setup() {
        Item item1 = new Item(1L, "RAM memory", new BigDecimal(100.00), 20);
        Item item2 = new Item(2L, "Hard drive", new BigDecimal(200.00), 300);
        Item item3 = new Item(3L, "Processor", new BigDecimal(70.00), 50);
        mockRepository = new HashMap<>();
        mockRepository.put(item1.getId(), item1);
        mockRepository.put(item2.getId(), item2);
        mockRepository.put(item3.getId(), item3);
    }

    public Item getItem(long id) {
        return mockRepository.get(id);
    }

    public BigDecimal getPrice(long id) {
        return mockRepository.get(id).getPrice();
    }

    public int getQuantity(long id) {
        return mockRepository.get(id).getQuantity();
    }
}
