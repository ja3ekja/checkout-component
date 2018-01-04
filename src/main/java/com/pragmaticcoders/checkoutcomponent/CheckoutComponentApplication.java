package com.pragmaticcoders.checkoutcomponent;

import com.pragmaticcoders.checkoutcomponent.repositories.MockRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CheckoutComponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckoutComponentApplication.class, args);

        MockRepository.setup();
    }
}
