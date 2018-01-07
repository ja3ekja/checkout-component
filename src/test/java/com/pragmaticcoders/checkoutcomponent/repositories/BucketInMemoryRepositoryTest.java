package com.pragmaticcoders.checkoutcomponent.repositories;

import com.pragmaticcoders.checkoutcomponent.model.TransactionItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BucketInMemoryRepositoryTest {

    @InjectMocks
    private BucketInMemoryRepository bucketRepository;

    private TransactionItem item1;
    private Set<TransactionItem> bucketSet;
    private UUID uuid;

    @Before
    public void setUp() {
        uuid = UUID.randomUUID();
        item1 = new TransactionItem(uuid, 1L, "RAM memory", new BigDecimal(100.00));
        bucketSet = new HashSet<>();
        bucketSet.add(item1);

        bucketRepository.addItem(item1);
    }

    @After
    public void after() {
        bucketRepository.clearBucket();
    }

    @Test
    public void getItemsTest() throws Exception {
        //Given
        //When
        Set<TransactionItem> items = bucketRepository.getItems();
        //Then
        assertThat(items, is(bucketSet));
    }

    @Test
    public void addItemTest() throws Exception {
        //Given
        Set<TransactionItem> mockedTwoItemsSet = bucketSet;
        mockedTwoItemsSet.add(item1);
        //When
        bucketRepository.addItem(item1);
        //Then
        assertThat(bucketRepository.getItems(), is(mockedTwoItemsSet));
    }

    @Test
    public void clearBucketTest() throws Exception {
        //Given
        //When
        bucketRepository.clearBucket();
        //Then
        assertThat(bucketRepository.getItems(), is(new HashSet<TransactionItem>()));
    }

    @Test
    public void getTotalAmountTest() throws Exception {
        //Given
        TransactionItem item2 = new TransactionItem(UUID.randomUUID(), 1L, "RAM memory", new BigDecimal(100.00));
        bucketRepository.addItem(item2);
        //When
        BigDecimal totalAmount = bucketRepository.getTotalAmount();
        //Then
        assertThat(totalAmount, is(new BigDecimal(200.00)));
    }

}