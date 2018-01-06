package com.pragmaticcoders.checkoutcomponent.controllers;

import com.pragmaticcoders.checkoutcomponent.model.Receipt;
import com.pragmaticcoders.checkoutcomponent.services.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/receipt")
public class ReceiptController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptController.class);

    private final ReceiptService receiptService;

    @RequestMapping(value = "/get-receipt/{receiptId}", method = RequestMethod.GET)
    public Receipt getReceipt(@PathVariable String receiptName) {
        LOGGER.debug("Get receipt with id %s.", receiptName);
        return receiptService.getReceipt(receiptName);
    }

    @RequestMapping(value = "/create-receipt", method = RequestMethod.GET)
    public Receipt create() {
        LOGGER.debug("Get total amount of bucket");
        LOGGER.debug("Create receipt.");
        return receiptService.createAndSave();
    }
}
