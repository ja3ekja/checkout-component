package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemsService.class);

    private final ItemRepository itemRepository;

    public Item getItem(long id) {
        LOGGER.debug("Select item with id: %s from repository", id);
        return itemRepository.getItem(id);
    }

    public BigDecimal getPrice(Long id) {
        LOGGER.debug("Select price of item with id: %s from repository", id);
        return itemRepository.getPrice(id);
    }

    public int getQuantity(Long id) {
        LOGGER.debug("Select quantity of item with id: %s from repository", id);
        return itemRepository.getQuantity(id);
    }
}
