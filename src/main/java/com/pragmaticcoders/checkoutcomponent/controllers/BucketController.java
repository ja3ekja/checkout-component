package com.pragmaticcoders.checkoutcomponent.controllers;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.services.BucketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/scan")
public class BucketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketController.class);

    private final BucketService bucketService;

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public BigDecimal scan(@PathVariable Long itemId) {
        LOGGER.debug("Scan and add item with id: %s to bucket.", itemId);
        return bucketService.scan(itemId);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public List<Item> checkBucket() {
        LOGGER.debug("List items from bucket");
        return bucketService.getItems();
    }

    @RequestMapping(value = "/sum", method = RequestMethod.GET)
    public BigDecimal bucketSum() {
        LOGGER.debug("Get total amount of bucket");
        return bucketService.getTotalAmount();
    }
}
