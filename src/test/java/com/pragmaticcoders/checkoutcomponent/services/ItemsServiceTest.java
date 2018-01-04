package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.repositories.MockRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsServiceTest {

    @InjectMocks
    private ItemsService itemsService;
    @Mock
    private MockRepository mockRepository;

    @Test
    public void getItemTest() {
        //Given
        Item item1 = new Item(1L, "RAM memory", new BigDecimal(100.00), 20);
        when(mockRepository.getItem(1L)).thenReturn(item1);
        //When
        Item item = itemsService.getItem(1L);
        //Then
        assertThat(item, is(item1));
    }

    @Test
    public void getPriceTest() {
        //Given
        when(mockRepository.getPrice(2L)).thenReturn(new BigDecimal(200.00));
        //When
        BigDecimal price = itemsService.getPrice(2L);
        //Then
        assertThat(price, is(new BigDecimal(200.00)));
    }

    @Test
    public void getQuantity() {
        //Given
        when(mockRepository.getQuantity(3L)).thenReturn(50);
        //When
        int quantity = itemsService.getQuantity(3L);
        //Then
        assertThat(quantity, is(50));
    }

}