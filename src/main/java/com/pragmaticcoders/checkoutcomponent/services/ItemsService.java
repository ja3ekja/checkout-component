package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.exceptions.ItemNotExistException;
import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.repositories.ItemRepository;
import com.pragmaticcoders.checkoutcomponent.repositories.PriceOnly;
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

    private final ItemRepository repository;

    public Item getItem(Long id) throws ItemNotExistException {
        LOGGER.debug("Select item with id: %s from repository", id);
        return repository.findById(id).orElseThrow(() -> new ItemNotExistException("Item not exist."));
    }

    public BigDecimal getPrice(Long id) throws ItemNotExistException {
        LOGGER.debug("Select price of item with id: %s from repository", id);
        PriceOnly priceOnly = repository.getPriceById(id).orElseThrow(() -> new ItemNotExistException("Item not exist."));
        return priceOnly.getPrice();
    }

    public Item saveItem(Item item) {
        LOGGER.debug("Add Item" + item + "to repository");
        return repository.save(item);
    }
}
