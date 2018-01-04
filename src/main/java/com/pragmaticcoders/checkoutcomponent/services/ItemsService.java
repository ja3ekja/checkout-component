package com.pragmaticcoders.checkoutcomponent.services;

import com.pragmaticcoders.checkoutcomponent.model.Item;
import com.pragmaticcoders.checkoutcomponent.repositories.MockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ItemsService {

    private final MockRepository mockRepository;

    public Item getItem(long id) {
    return mockRepository.getItem(id);
    }

    public int getQuantity(Long itemId) {
        return mockRepository.getQuantity(itemId);
    }

    public BigDecimal getPrice(Long itemId) {
        return mockRepository.getPrice(itemId);
    }
}
