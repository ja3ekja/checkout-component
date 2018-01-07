package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BucketItemServiceTest {

    @InjectMocks
    private BucketItemService bucketItemService;

    @Test
    public void parseTest() throws Exception {
        //Given
        BigDecimal price = new BigDecimal(1111.00);
        String szprotki = "Szprotki";
        long id = 10L;
        Item item = new Item(id, szprotki, price);
        //When
        TransactionItem transactionItem = bucketItemService.parse(item);
        //Them
        assertThat(transactionItem.getItemId(), is(id));
        assertThat(transactionItem.getName(), is(szprotki));
        assertThat(transactionItem.getPrice(), is(price));
    }

}