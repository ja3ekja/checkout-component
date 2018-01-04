package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.repositories.BucketInMemoryRepository;
import com.pragmaticcoders.checkoutcomponent.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BucketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketService.class);

    private final BucketInMemoryRepository bucketRepository;
    private final ItemRepository itemRepository;


    public BigDecimal scan(Long itemId) {
        bucketRepository.addItem(getItemFromItemRepository(itemId));
        return getTotalAmount();
    }

    public Collection<Item> getItems() {
        return bucketRepository.getItems();
    }

    public BigDecimal getTotalAmount() {
        return bucketRepository.getTotalAmount();
    }

    public Item getItemFromItemRepository(Long itemId){
        return itemRepository.getItem(itemId);
    }
}

