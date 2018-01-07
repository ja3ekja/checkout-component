package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.exceptions.ItemNotExistException;
import com.pragmaticcoders.checkoutcomponent.exceptions.PromotionNotExistException;
import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.model.Promotion;
import com.pragmaticcoders.checkoutcomponent.repositories.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PromotionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionService.class);

    private final PromotionRepository promotionRepository;

    private final ItemsService itemsService;

    public Promotion getPromotionById(Long itemId) throws PromotionNotExistException {
        return promotionRepository.findByItem(new Item(itemId)).orElseThrow(() -> new PromotionNotExistException("Promotion not exist."));
    }

    public Promotion savePromotion(BigDecimal price, Integer quantity, Long itemId) throws ItemNotExistException {
        LOGGER.debug("Add promotion for product id: " + itemId + " price " + price + " for quantity " + quantity);
        Promotion newPromo = createPromotion(price, quantity, itemId);
        return promotionRepository.save(newPromo);
    }

    private Promotion createPromotion(BigDecimal price, Integer quantity, Long itemId) throws ItemNotExistException {
        Item item = itemsService.getItem(itemId);
        return new Promotion(price, quantity, item);
    }

    public Promotion getPromotion(Long promotionId) throws PromotionNotExistException {
        LOGGER.debug("Get promotion id: " + promotionId);
        return promotionRepository.findById(promotionId).orElseThrow(() -> new PromotionNotExistException("Promotion not exist."));
    }

    public List<Promotion> getAllPromotions() {
        LOGGER.debug("Get all promotion");
        return promotionRepository.findAll();
    }

    public void calculatePromotion(Long itemId) {

    }
}
