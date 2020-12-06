package com.roche.products.api.rest;

import com.roche.products.common.response.GetProductResponse;
import com.roche.products.core.model.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductResourceUtilsTest {

    @Test
    public void testGetProductResponseFromProduct() {

        Long id = new Random().nextLong();
        String name = UUID.randomUUID().toString();
        String sku = UUID.randomUUID().toString();
        Integer priceInCents = new Random().nextInt(100);
        Boolean isDeleted = true;
        LocalDateTime created = LocalDateTime.of(2000, 1, 1, 10, 15);
        LocalDateTime updated = LocalDateTime.now();

        Product product = new Product(id,sku, name, priceInCents, isDeleted);
        product.setCreatedAt(created);
        product.setUpdatedAt(updated);

        GetProductResponse getProductResponse = ProductResource.createGetProductResponseFromProduct(product);

        assertThat(getProductResponse.getId()).isEqualTo(id);
        assertThat(getProductResponse.getName()).isEqualTo(name);
        assertThat(getProductResponse.getSku()).isEqualTo(sku);
        assertThat(getProductResponse.getPriceInCents()).isEqualTo(priceInCents);
        assertThat(getProductResponse.getIsDeleted()).isEqualTo(isDeleted);
        assertThat(getProductResponse.getCreatedAt()).isEqualTo(created);
        assertThat(getProductResponse.getUpdatedAt()).isEqualTo(updated);
    }
}
