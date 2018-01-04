package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.repositories.ItemRepository;
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

    @Test
    public void getItemTest() {
        //Given
        Item item1 = new Item(1L, "RAM memory", new BigDecimal(100.00), 20);
        when(itemRepository.getItem(1L)).thenReturn(item1);
        //When
        Item item = itemsService.getItem(1L);
        //Then
        assertThat(item, is(item1));
    }

    @Test
    public void getPriceTest() {
        //Given
        when(itemRepository.getPrice(2L)).thenReturn(new BigDecimal(200.00));
        //When
        BigDecimal price = itemsService.getPrice(2L);
        //Then
        assertThat(price, is(new BigDecimal(200.00)));
    }

    @Test
    public void getQuantity() {
        //Given
        when(itemRepository.getQuantity(3L)).thenReturn(50);
        //When
        int quantity = itemsService.getQuantity(3L);
        //Then
        assertThat(quantity, is(50));
    }
}