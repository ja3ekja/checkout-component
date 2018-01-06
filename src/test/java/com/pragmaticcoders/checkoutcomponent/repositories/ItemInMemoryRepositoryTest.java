package com.pragmaticcoders.checkoutcomponent.repositories;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemInMemoryRepositoryTest {

    @InjectMocks
    private ItemInMemoryRepository itemInMemoryRepository;

    @Test
    public void getItemTest() {
        //Given
        Item item1 = new Item(1L, "RAM memory", new BigDecimal(100.00));
        //When
        Item resultItem = itemInMemoryRepository.getItem(1L);
        //Then
        assertThat(resultItem, is(item1));
    }

    @Test
    public void getPriceTest() {
        //Given
        //When
        BigDecimal price = itemInMemoryRepository.getPrice(2L);
        //Then
        assertThat(price, is(new BigDecimal(200.00)));
    }
}