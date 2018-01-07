package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.exceptions.ItemNotExistException;
import com.pragmaticcoders.checkoutcomponent.exceptions.PromotionNotExistException;
import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.model.Promotion;
import com.pragmaticcoders.checkoutcomponent.repositories.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PromotionService {

    private final PromotionRepository promotionRepository;

    private final ItemsService itemsService;

    public Promotion getPromotionForItem(Long itemId) {
        return null;
    }

    public Promotion saveItem(BigDecimal price, Integer quantity, Long itemId) throws ItemNotExistException {
        Promotion newPromo = createPromotion(price, quantity, itemId);
        return promotionRepository.save(newPromo);
    }

    private Promotion createPromotion(BigDecimal price, Integer quantity, Long itemId) throws ItemNotExistException {
        Item item = itemsService.getItem(itemId);
        return new Promotion(price, quantity, item);
    }

    public Promotion getPromotion(Long promotionId) throws PromotionNotExistException {
        return promotionRepository.findById(promotionId).orElseThrow(() -> new PromotionNotExistException("Promotion not exist."));
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }
}
