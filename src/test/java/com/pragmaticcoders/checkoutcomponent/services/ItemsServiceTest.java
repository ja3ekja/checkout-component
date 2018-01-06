package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.repositories.ItemInMemoryRepository;
import com.pragmaticcoders.checkoutcomponent.repositories.ItemRepository;
import com.pragmaticcoders.checkoutcomponent.repositories.PriceOnly;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemsServiceTest {

    @InjectMocks
    private ItemsService itemsService;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private PriceOnly priceOnly;

    @Test
    public void getItemTest() {
        //Given
        Item item1 = new Item(1L, "RAM memory", new BigDecimal(100.00));
        when(itemRepository.findById(1L)).thenReturn(item1);
        //When
        Item item = itemsService.getItem(1L);
        //Then
        assertThat(item, is(item1));
    }

    @Test
    public void getPriceTest() {
        //Given
        when(itemRepository.getPriceById(2L)).thenReturn(priceOnly);
        when(priceOnly.getPrice()).thenReturn(new BigDecimal(200.00));
        //When
        BigDecimal price = itemsService.getPrice(2L);
        //Then
        assertThat(price, is(new BigDecimal(200.00)));
    }
}