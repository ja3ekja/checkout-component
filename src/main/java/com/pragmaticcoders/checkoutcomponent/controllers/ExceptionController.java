package com.pragmaticcoders.checkoutcomponent.controllers;

import com.pragmaticcoders.checkoutcomponent.exceptions.ItemNotExistException;
import com.pragmaticcoders.checkoutcomponent.exceptions.PromotionNotExistException;
import com.pragmaticcoders.checkoutcomponent.exceptions.ReceiptNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(ItemNotExistException.class)
    public ResponseEntity<VndErrors> itemNotFoundException(final ItemNotExistException e) {
        LOGGER.debug("Item controller Exception: " + e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ReceiptNotExistException.class)
    public ResponseEntity<VndErrors> receiptNotFoundException(final ReceiptNotExistException e) {
        LOGGER.debug("Receipt controller Exception: " + e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(PromotionNotExistException.class)
    public ResponseEntity<VndErrors> receiptNotFoundException(final PromotionNotExistException e) {
        LOGGER.debug("Promotion controller Exception: " + e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    private ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<VndErrors> apiException(final Exception e) {
        LOGGER.debug("Api exception: " + e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, "Api exception: " + e.getMessage());
    }

}



