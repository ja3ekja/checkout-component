package com.pragmaticcoders.checkoutcomponent.repositories;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MockRepositoryTest {

    @InjectMocks
    private MockRepository mockRepository;

    @Before
    public void setUp() {
        MockRepository.setup();
    }

    @Test
    public void getItemTest() {
        //Given
        Item item1 = new Item(1L, "RAM memory", new BigDecimal(100.00), 20);
        //When
        Item resultItem = mockRepository.getItem(1L);
        //Then
        assertThat(resultItem, is(item1));
    }

    @Test
    public void getPriceTest() {
        //Given
        //When
        BigDecimal price = mockRepository.getPrice(2L);
        //Then
        assertThat(price, is(new BigDecimal(200.00)));
    }

    @Test
    public void getQuantity() {
        //Given
        //When
        int quantity = mockRepository.getQuantity(3L);
        //Then
        assertThat(quantity, is(50));
    }
}