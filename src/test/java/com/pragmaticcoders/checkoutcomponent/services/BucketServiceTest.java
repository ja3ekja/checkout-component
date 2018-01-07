package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
import com.pragmaticcoders.checkoutcomponent.repositories.BucketInMemoryRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BucketServiceTest {

    @InjectMocks
    private BucketService bucketService;

    @Mock
    private BucketItemService bucketItemService;

    @Mock
    private BucketInMemoryRepository bucketRepository;

    @Mock
    private ItemsService itemsService;

    private Item item1;
    private TransactionItem transactionItem;

    @Before
    public void setUp() {
        item1 = new Item("jogurt", new BigDecimal(199.00));
        transactionItem = new TransactionItem();
    }

    @Ignore
    @Test
    public void scanTest() throws Exception {
        //Given
        when(bucketItemService.parse(1L, "", new BigDecimal(1))).thenReturn(new TransactionItem());
        when(bucketRepository.getTotalAmount()).thenReturn(new BigDecimal(100.00));
        //When
        BigDecimal totalAmount = bucketService.scanReturnTotalAmount(2L);
        //Then
        assertThat(totalAmount, is(new BigDecimal(100.00)));
    }

    @Test
    public void getItemsTest() throws Exception {
        //Given
        Set<TransactionItem> mockedResult = new HashSet<>();
        mockedResult.add(transactionItem);
        when(bucketRepository.getItems()).thenReturn(mockedResult);
        //When
        Set<TransactionItem> items = bucketService.getItems();
        //Then
        assertThat(items, is(mockedResult));
    }

    @Test
    public void getTotalAmountTest() throws Exception {
        //Given
        when(bucketRepository.getTotalAmount()).thenReturn(new BigDecimal(200.00));
        //When
        BigDecimal totalAmount = bucketService.getTotalAmount();
        //Then
        assertThat(totalAmount, is(new BigDecimal(200.00)));
    }

    @Test
    public void cleanBucketTest(){
        //Given
        when(bucketRepository.getTotalAmount()).thenReturn(new BigDecimal(0));
        //When
        BigDecimal totalAmount = bucketService.cleanBucket();
        //Then
        assertThat(totalAmount, is(new BigDecimal(0)));
    }

}