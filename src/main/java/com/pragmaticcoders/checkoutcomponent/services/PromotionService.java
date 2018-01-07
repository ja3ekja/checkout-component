package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.exceptions.ItemNotExistException;
import com.pragmaticcoders.checkoutcomponent.exceptions.PromotionNotExistException;
import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.model.Promotion;
import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
import com.pragmaticcoders.checkoutcomponent.repositories.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PromotionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionService.class);
    private final static String PROMOTION_NAME = "promotion";
    private final static BigDecimal ZERO = BigDecimal.ZERO;
    private final PromotionRepository promotionRepository;

    private final ItemsService itemsService;
    private final BucketItemService bucketItemService;

    public Promotion getPromotionByItemId(Long itemId) {
        return promotionRepository.findByItem(new Item(itemId));// TODO Deal with nonexistent promotion
    }

    public Promotion savePromotion(BigDecimal price, Integer quantity, Long itemId) throws ItemNotExistException {
        LOGGER.debug("Add promotion for product id: " + itemId + " amount " + price + " for quantity " + quantity);
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

    /*
     * pattern = ( ( itemsNumber / promotionQuantity ) - promotionItemsNumber ) * ( promotionAmount - ( itemPrice *  ))
     * this ( itemsNumberWithoutPromotion / PromotionQuantity ) is rounded down
     */
    public void calculatePromotion(TransactionItem transactionItem, Long itemId, Set<TransactionItem> bucketItems) {

        Promotion promotion = getPromotionByItemId(itemId);
        BigDecimal itemsNumberWithoutPromotion = extractBucketItemForItemId(itemId, bucketItems);
        BigDecimal promotionItemsNumber = extractPromotionFromBucketForItemId(itemId, bucketItems);
        BigDecimal binaryFlag = flagIfShouldAddPromotionDeduction(promotion, itemsNumberWithoutPromotion, promotionItemsNumber);
        BigDecimal promotionAmount = calculatePromotionAmount(transactionItem, promotion);

        if (!binaryFlag.equals(ZERO)) bucketItemService.convertAndAddItem(itemId, PROMOTION_NAME, promotionAmount);
    }

    private BigDecimal flagIfShouldAddPromotionDeduction(Promotion promotion, BigDecimal itemsNumberWithoutPromotion, BigDecimal promotionItemsNumber) {
        return itemsNumberWithoutPromotion
                .divide(new BigDecimal(promotion.getQuantity()), 0, RoundingMode.DOWN).subtract(promotionItemsNumber);
    }

    private BigDecimal calculatePromotionAmount(TransactionItem transactionItem, Promotion promotion) {
        return promotion.getAmount()
                .subtract(transactionItem.getPrice().multiply(new BigDecimal(promotion.getQuantity())));
    }

    private BigDecimal extractPromotionFromBucketForItemId(Long itemId, Set<TransactionItem> bucketItems) {
        return new BigDecimal(bucketItems.stream()
                .filter(x -> x.getItemId().equals(itemId)).filter(y -> y.getName().equals(PROMOTION_NAME)).count());
    }

    private BigDecimal extractBucketItemForItemId(Long itemId, Set<TransactionItem> bucketItems) {
        return new BigDecimal(bucketItems.stream()
                .filter(x -> x.getItemId().equals(itemId)).filter(y -> !y.getName().equals(PROMOTION_NAME)).count());
    }
}
