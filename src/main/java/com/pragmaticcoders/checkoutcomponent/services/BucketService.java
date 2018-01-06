package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.repositories.BucketInMemoryRepository;
import com.pragmaticcoders.checkoutcomponent.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BucketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketService.class);

    private final BucketInMemoryRepository bucketRepository;
    private final ItemRepository itemRepository;
    private final BucketItemService bucketItemService;


    public BigDecimal scan(Long itemId) {
        LOGGER.debug("Scan and add item with id: %s to bucket.", itemId);

        TransactionItem transactionItem = bucketItemService.parse(getItemFromItemRepository(itemId));
        bucketRepository.addItem(transactionItem);
        return getTotalAmount();
    }

    public Set<TransactionItem> getItems() {
        LOGGER.debug("List items from bucket");
        return bucketRepository.getItems();
    }

    public BigDecimal getTotalAmount() {
        LOGGER.debug("Get total amount of bucket");
        return bucketRepository.getTotalAmount();
    }

    public Item getItemFromItemRepository(Long itemId) {
        LOGGER.debug("Get item : %s", itemId);
        return itemRepository.findById(itemId);
    }
}

