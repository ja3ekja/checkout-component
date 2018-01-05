package com.pragmaticcoders.checkoutcomponent.controllers;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.services.ItemsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/checkout")
public class ItemsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemsController.class);

    private final ItemsService itemsService;

    @RequestMapping(value = "/get-item/{itemId}", method = RequestMethod.GET)
    public Item getItem(@PathVariable Long itemId) {
        LOGGER.debug("Get item with id: %s", itemId);
        return itemsService.getItem(itemId);
    }

    @RequestMapping(value = "/get-price/{itemId}", method = RequestMethod.GET)
    public BigDecimal getPrice(@PathVariable Long itemId) {
        LOGGER.debug("Get price of item with id: %s", itemId);
        return itemsService.getPrice(itemId);
    }

    @RequestMapping(value = "/get-quantity/{itemId}", method = RequestMethod.GET)
    public int getQuantity(@PathVariable Long itemId) {
        LOGGER.debug("Get quantity of item with id: %s", itemId);
        return itemsService.getQuantity(itemId);
    }

}