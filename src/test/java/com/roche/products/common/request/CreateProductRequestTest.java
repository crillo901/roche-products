package com.roche.products.common.request;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateProductRequestTest {

    @Test
    public void testAllFieldsMappedCorrectly(){

        Long id = new Random().nextLong();
        String sku = UUID.randomUUID().toString();
        String name = UUID.randomUUID().toString();
        Integer priceInCents = new Random().nextInt(1000);
        Boolean isDeleted = new Random().nextBoolean();

        CreateProductRequest createProductRequest = new CreateProductRequest(id,sku,name,priceInCents,isDeleted);

        assertThat(createProductRequest.getId()).isEqualTo(id);
        assertThat(createProductRequest.getSku()).isEqualTo(sku);
        assertThat(createProductRequest.getName()).isEqualTo(name);
        assertThat(createProductRequest.getPriceInCents()).isEqualTo(priceInCents);
        assertThat(createProductRequest.getIsDeleted()).isEqualTo(isDeleted);

        createProductRequest.setId(id);
        createProductRequest.setName(name);
        createProductRequest.setSku(sku);
        createProductRequest.setPriceInCents(priceInCents);
        createProductRequest.setIsDeleted(isDeleted);

        assertThat(createProductRequest.getId()).isEqualTo(id);
        assertThat(createProductRequest.getSku()).isEqualTo(sku);
        assertThat(createProductRequest.getName()).isEqualTo(name);
        assertThat(createProductRequest.getPriceInCents()).isEqualTo(priceInCents);
        assertThat(createProductRequest.getIsDeleted()).isEqualTo(isDeleted);
    }
}
