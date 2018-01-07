package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.exceptions.ReceiptNotExistException;
import com.pragmaticcoders.checkoutcomponent.model.Receipt;
import com.pragmaticcoders.checkoutcomponent.repositories.BucketInMemoryRepository;
import com.pragmaticcoders.checkoutcomponent.repositories.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReceiptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptService.class);

    private final ReceiptRepository receiptRepository;
    private final BucketInMemoryRepository bucketRepository;
    private final PaymentServiceMock paymentService;

    public Receipt getReceipt(Long id) throws ReceiptNotExistException {
        return receiptRepository.findById(id).orElseThrow(() -> new ReceiptNotExistException("Receipt not exist."));
    }

    @Transactional
    public Receipt createAndSaveAndClean() {
        Receipt receipt = create();
        LOGGER.debug("Receipt created");
        paymentService.payForReceiptMock();
        LOGGER.debug("Successful payment");
        receiptRepository.save(receipt);
        LOGGER.debug("Receipt saved");
        bucketRepository.clearBucket();
        LOGGER.debug("Receipt cleaned");
        return receipt;
    }

    private Receipt create() {
        Receipt receipt = new Receipt();
        receipt.setName("FV-" + generateReceiptId() + "-2018");
        receipt.setDate(LocalDateTime.now());
        receipt.setTransactionItems(bucketRepository.getItems());
        receipt.setTotalPrice(bucketRepository.getTotalAmount());

        return receipt;
    }

    private int generateReceiptId() {
        return (int) (10000 * Math.random());
    }
}
