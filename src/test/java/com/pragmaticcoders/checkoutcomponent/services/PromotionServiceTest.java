package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.model.Promotion;
import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
import com.pragmaticcoders.checkoutcomponent.repositories.PromotionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PromotionServiceTest {

    @InjectMocks
    private PromotionService promotionService;

    @Mock
    private BucketService bucketService;

    @Mock
    private PromotionRepository promotionRepository;

    @Mock
    private BucketItemService bucketItemService;

    private Set<TransactionItem> bucketItems;
    private TransactionItem transactionItem1;
    private TransactionItem transactionItem2;
    private TransactionItem transactionItem3;
    private Promotion promotion1;
    private Item item;

    @Before
    public void setUp() {
        bucketItems = new HashSet<>();
        transactionItem1 = new TransactionItem(1L, "dummy juice", new BigDecimal(4.00));
        transactionItem2 = new TransactionItem(9L, "dummy bar", new BigDecimal(1.80));
        transactionItem3 = new TransactionItem(1L, "promotion", new BigDecimal(-2.00));
        item = new Item(1L, "dummy bar", new BigDecimal(1.80));
        promotion1 = new Promotion(1L, new BigDecimal(10.00), 3, item);
        when(promotionRepository.findByItem(any(Item.class))).thenReturn(Optional.of(promotion1));
    }

    @Test
    public void calculatePromotionCheckBucketTest() {
        //Given
        bucketItems.add(transactionItem1);
        //when
        promotionService.calculatePromotion(transactionItem1, 1L, bucketItems);
        //then
    }

}
