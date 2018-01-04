package com.pragmaticcoders.checkoutcomponent.repositories;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import org.hamcrest.number.BigDecimalCloseTo;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BucketInMemoryRepositoryTest {

    @InjectMocks
    private BucketInMemoryRepository bucketRepository;

    private Item item1;

    private List<Item> bucketList;

    @Before
    public void setUp() {
        item1 = new Item(1L, "RAM memory", new BigDecimal(100.00), 20);
        bucketList = new ArrayList<>();
        bucketList.add(item1);

        bucketRepository.addItem(item1);
    }

    @After
    public void after(){
        bucketRepository.clearBucket();
    }

    @Test
    public void getItemsTest() throws Exception {
        //Given
        //When
        List<Item> items = bucketRepository.getItems();
        //Then
        assertThat(items, is(bucketList));
    }

    @Test
    public void addItemTest() throws Exception {
        //Given
        List<Item> twoItemsList= bucketList;
        twoItemsList.add(item1);
        //When
        bucketRepository.addItem(item1);
        //Then
        assertThat(bucketRepository.getItems(), is(twoItemsList));
    }

    @Test
    public void clearBucketTest() throws Exception {
        //Given
        //When
        bucketRepository.clearBucket();
        //Then
        assertThat(bucketRepository.getItems(), is(new ArrayList<Item>()));
    }

    @Test
    public void getTotalAmountTest() throws Exception {
        //Given
        bucketRepository.addItem(item1);
        //When
        BigDecimal totalAmount = bucketRepository.getTotalAmount();
        //Then
        assertThat(totalAmount, is(new BigDecimal(200.00)));
    }

}