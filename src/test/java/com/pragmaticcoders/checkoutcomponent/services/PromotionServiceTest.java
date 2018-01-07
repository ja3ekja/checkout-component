package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.model.Promotion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PromotionServiceTest {

    @InjectMocks
    private PromotionService promotionService;

    @Test
    public void getPromotionForItemTest() {
        //Given
        Promotion promotionMock = new Promotion(new Item(), 2, new BigDecimal(5.00));
        //when
        Promotion promotion = promotionService.getPromotionForItem(3L);
        //then
    }

    @Test
    public void getAllPromotionsTest() {
        //Given
        Promotion promotion1 = new Promotion(new Item(), 2, new BigDecimal(5.00));
        Promotion promotion2 = new Promotion(new Item(), 2, new BigDecimal(5.00));
        //when
        //then
    }
    
}
