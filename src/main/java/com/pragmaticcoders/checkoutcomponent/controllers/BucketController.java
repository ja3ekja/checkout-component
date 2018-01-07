package com.pragmaticcoders.checkoutcomponent.controllers;

import com.pragmaticcoders.checkoutcomponent.exceptions.ItemNotExistException;
import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
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
import java.util.Set;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/bucket")
public class BucketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BucketController.class);

    private final BucketService bucketService;

    @RequestMapping(value = "/scan-item/{itemId}", method = RequestMethod.GET)
    public BigDecimal scanReturnTotalAmount(@PathVariable Long itemId) throws ItemNotExistException {
        LOGGER.debug("Scan and add item with id: %s to bucket.", itemId);
        return bucketService.scanReturnTotalAmount(itemId);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Set<TransactionItem> checkBucket() {
        LOGGER.debug("List items from bucket");
        return bucketService.getItems();
    }

    @RequestMapping(value = "/sum", method = RequestMethod.GET)
    public BigDecimal bucketTotalAmount() {
        LOGGER.debug("Get total amount of bucket");
        return bucketService.getTotalAmount();
    }

    @RequestMapping(value = "/clean", method = RequestMethod.GET)
    public BigDecimal cleanBucket() {
        LOGGER.debug("Clean bucket");
        return bucketService.cleanBucket();
    }
}
