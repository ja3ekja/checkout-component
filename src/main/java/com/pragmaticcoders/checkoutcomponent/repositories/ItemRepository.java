package com.pragmaticcoders.checkoutcomponent.repositories;


import com.pragmaticcoders.checkoutcomponent.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findById(Long id);

    PriceOnly getPriceById(Long id);

}
