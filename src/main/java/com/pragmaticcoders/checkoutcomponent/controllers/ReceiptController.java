package com.pragmaticcoders.checkoutcomponent.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/receipt")
public class ReceiptController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptController.class);
}
