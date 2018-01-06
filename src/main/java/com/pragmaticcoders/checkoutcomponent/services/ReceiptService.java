package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Receipt;
import com.pragmaticcoders.checkoutcomponent.repositories.BucketInMemoryRepository;
import com.pragmaticcoders.checkoutcomponent.repositories.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReceiptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemsService.class);

    private final ReceiptRepository receiptRepository;

    private final BucketInMemoryRepository bucketRepository;

    public Receipt getReceipt(String receiptName) {
        return receiptRepository.findByName(receiptName);
    }

    public Receipt createAndSave() {
        Receipt receipt = create();
        return receiptRepository.save(receipt);
    }

    public Receipt create() {
        Receipt receipt = new Receipt();
        receipt.setName("FV/" + receipt.getId() + "/2018");
        receipt.setDate(new Date());
        receipt.setTransactionItems(bucketRepository.getItems());
        receipt.setTotalPrice(bucketRepository.getTotalAmount());

        return receipt;
    }
}
