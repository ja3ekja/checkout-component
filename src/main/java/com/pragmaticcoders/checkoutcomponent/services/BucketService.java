package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.exceptions.ItemNotExistException;
import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.model.Promotion;
import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
import com.pragmaticcoders.checkoutcomponent.repositories.BucketInMemoryRepository;
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
    private final ItemsService itemsService;
    private final BucketItemService bucketItemService;
    private final PromotionService promotionService;


    public BigDecimal scanReturnTotalAmount(Long itemId) throws ItemNotExistException {
        LOGGER.debug("Scan and add item with id: %s to bucket.", itemId);
        TransactionItem transactionItem = bucketItemService.parse(getItemFromItemRepository(itemId));
        bucketRepository.addItem(transactionItem);
        promotionService.calculatePromotion(itemId);
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

    private Item getItemFromItemRepository(Long itemId) throws ItemNotExistException {
        LOGGER.debug("Get item : %s", itemId);
        return itemsService.getItem(itemId);
    }

    public BigDecimal cleanBucket() {
        LOGGER.debug("Get item : %s");
        bucketRepository.clearBucket();
        return bucketRepository.getTotalAmount();
    }
}

