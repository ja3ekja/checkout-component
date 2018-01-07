package com.pragmaticcoders.checkoutcomponent.controllers;

import com.pragmaticcoders.checkoutcomponent.exceptions.ItemNotExistException;
import com.pragmaticcoders.checkoutcomponent.exceptions.PromotionNotExistException;
import com.pragmaticcoders.checkoutcomponent.model.Promotion;
import com.pragmaticcoders.checkoutcomponent.services.PromotionService;
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
@RequestMapping("/promotion")
public class PromotionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionController.class);

    private final PromotionService promotionService;

    @RequestMapping(value = "/add-promotion/price/{price}/quantity/{quantity}/item-id/{id}", method = RequestMethod.GET)
    public Promotion addPromotion(@PathVariable BigDecimal price, @PathVariable Integer quantity, @PathVariable Long id) throws ItemNotExistException {
        LOGGER.debug("Add promotion for product id: " + id + " amount " + price + " for quantity " + quantity);
        return promotionService.savePromotion(price, quantity, id);
    }

    @RequestMapping(value = "/get-promotion/promotionId/{promotionId}", method = RequestMethod.GET)
    public Promotion getPromotion(@PathVariable Long promotionId) throws PromotionNotExistException {
        LOGGER.debug("Get promotion id: " + promotionId);
        return promotionService.getPromotion(promotionId);
    }

    @RequestMapping(value = "/get-all-promotion/", method = RequestMethod.GET)
    public List<Promotion> getAllPromotions() {
        LOGGER.debug("Get all promotion");
        return promotionService.getAllPromotions();
    }

    @RequestMapping(value = "/get-promotion/itemId/{itemId}", method = RequestMethod.GET)
    public Promotion getPromotionByItem(@PathVariable Long itemId) throws PromotionNotExistException {
        LOGGER.debug("Get promotion for item : " + itemId);
        return promotionService.getPromotionByItemId(itemId);
    }
}
