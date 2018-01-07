package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
import com.pragmaticcoders.checkoutcomponent.repositories.BucketInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BucketItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketItemService.class);

    private final BucketInMemoryRepository bucketRepository;

    TransactionItem parse(Long itemId, String name, BigDecimal price) {
        return new TransactionItem(itemId, name, price);

    }

    public TransactionItem convertAndAddItem(Long itemId, String name, BigDecimal price) {
        LOGGER.debug("Covert and add item with id: %s to bucket.", itemId);
        TransactionItem transactionItem = parse(itemId, name, price);
        bucketRepository.addItem(transactionItem);
        return transactionItem;
    }
}
