package com.pragmaticcoders.checkoutcomponent.controllers;

import com.pragmaticcoders.checkoutcomponent.exceptions.ItemNotExistException;
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

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/checkout")
public class ItemsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemsController.class);

    private final ItemsService itemsService;

    @RequestMapping(value = "/get-item/{id}", method = RequestMethod.GET)
    public Item getItem(@PathVariable Long id) throws ItemNotExistException {
        LOGGER.debug("Get item with id: %s", id);
        return itemsService.getItem(id);
    }

    @RequestMapping(value = "/get-price/{id}", method = RequestMethod.GET)
    public BigDecimal getPrice(@PathVariable Long id) throws ItemNotExistException {
        LOGGER.debug("Get price of item with id: %s", id);
        return itemsService.getPrice(id);
    }

    @RequestMapping(value = "/add-item/name/{name}/price/{price}", method = RequestMethod.GET)
    public Item addItem(@PathVariable String name, @PathVariable BigDecimal price) {
        LOGGER.debug("Add item "+ name + " name, price "+price);
        return itemsService.saveItem(new Item(name,price));
    }

    @RequestMapping("/")
    public String home()
    {
        return "Welcome!";
    }
}
