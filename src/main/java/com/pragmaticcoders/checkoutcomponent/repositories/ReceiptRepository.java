package com.pragmaticcoders.checkoutcomponent.repositories;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.annotation.SessionScope;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    Receipt findByName(String name);

    Receipt save(Receipt receipt);
}
