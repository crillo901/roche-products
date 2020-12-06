package com.roche.products.core.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {


    @Test
    public void testAllFieldsMappedCorrectly() {

        Long id = new Random().nextLong();
        String sku = UUID.randomUUID().toString();
        String name = UUID.randomUUID().toString();
        Integer priceInCents = new Random().nextInt(1000);
        Boolean isDeleted = new Random().nextBoolean();
        LocalDateTime created = LocalDateTime.now().minusDays(1L);
        LocalDateTime updated = LocalDateTime.now();

        Product product = new Product(id, sku, name, priceInCents, isDeleted);

        assertThat(product.getId()).isEqualTo(id);
        assertThat(product.getSku()).isEqualTo(sku);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPriceInCents()).isEqualTo(priceInCents);
        assertThat(product.getDeleted()).isEqualTo(isDeleted);

        product.setId(id);
        product.setSku(sku);
        product.setPriceInCents(priceInCents);
        product.setDeleted(isDeleted);
        product.setUpdatedAt(created);
        product.setCreatedAt(updated);

        assertThat(product.getId()).isEqualTo(id);
        assertThat(product.getSku()).isEqualTo(sku);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPriceInCents()).isEqualTo(priceInCents);
        assertThat(product.getDeleted()).isEqualTo(isDeleted);
    }

}
