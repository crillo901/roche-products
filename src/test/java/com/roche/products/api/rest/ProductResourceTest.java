package com.roche.products.api.rest;

import com.roche.RocheProductsApplicationTestBase;
import com.roche.products.common.request.CreateProductRequest;
import com.roche.products.common.response.GetProductListResponse;
import com.roche.products.common.response.GetProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ProductResourceTest extends RocheProductsApplicationTestBase {


    public GetProductResponse createNewProductForTesting() {

        CreateProductRequest createProductRequest = new CreateProductRequest(0L,
                UUID.randomUUID().toString(), UUID.randomUUID().toString(), new Random().nextInt(10000), false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateProductRequest> request = new HttpEntity<>(createProductRequest, headers);

        ResponseEntity<GetProductResponse> response = restTemplate.postForEntity(createURLWithPort(productsUri), request,
                GetProductResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotZero();

        return response.getBody();
    }

    public GetProductResponse getProductResponseForTesting(Long productId) {

        String url = createURLWithPort(productsUri + "/" + productId);
        ResponseEntity<GetProductResponse> response = restTemplate.getForEntity(url, GetProductResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(productId);

        return response.getBody();
    }


    @Test
    public void listTest() {
        //given:
        //assert api endpoint is working
        ResponseEntity<GetProductListResponse> initialProductListResponse = restTemplate.getForEntity(createURLWithPort(productsUri), GetProductListResponse.class);
        assertThat(initialProductListResponse).isNotNull();
        assertThat(initialProductListResponse.getBody()).isNotNull();

        createNewProductForTesting();
        createNewProductForTesting();

        //when:
        ResponseEntity<GetProductListResponse> response = restTemplate.getForEntity(createURLWithPort(productsUri), GetProductListResponse.class);

        //then:
        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getResponseList().size()).isGreaterThanOrEqualTo(initialProductListResponse.getBody().getResponseList().size() + 2);
    }

    @Test
    public void getProductByIdTest() {
        //given:
        //set up product for testing
        GetProductResponse productResponse = createNewProductForTesting();
        assertThat(productResponse).isNotNull();
        assertThat(productResponse.getId()).isPositive();

        //when:
        GetProductResponse getProductResponse = getProductResponseForTesting(productResponse.getId());

        //then:
        assertThat(getProductResponse).isNotNull();
        assertThat(getProductResponse).isNotNull();
        assertThat(getProductResponse.getId()).isEqualTo(productResponse.getId());
    }

    @Test
    public void updateProductTest() {
        //given:
        //set up product for testing
        GetProductResponse initialProduct = createNewProductForTesting();
        assertThat(initialProduct).isNotNull();
        assertThat(initialProduct.getId()).isPositive();

        String newName = UUID.randomUUID().toString();
        CreateProductRequest updateProductRequest = new CreateProductRequest(initialProduct.getId(),
                initialProduct.getSku(), newName, initialProduct.getPriceInCents(), initialProduct.getIsDeleted());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateProductRequest> request = new HttpEntity<>(updateProductRequest, headers);

        GetProductResponse updatedProductResponse = restTemplate.patchForObject(createURLWithPort(productsUri + "/" + updateProductRequest.getId()), request,
                GetProductResponse.class);

        assertThat(updatedProductResponse).isNotNull();
        assertThat(updatedProductResponse.getName()).isEqualTo(newName);

        GetProductResponse getProductResponse = getProductResponseForTesting(updatedProductResponse.getId());
        assertThat(getProductResponse).isNotNull();
        assertThat(getProductResponse.getName()).isEqualTo(newName);
    }

    @Test
    public void deleteProductTest() {
        //given:
        //set up product for testing
        GetProductResponse initialProduct = createNewProductForTesting();
        assertThat(initialProduct).isNotNull();
        assertThat(initialProduct.getId()).isPositive();

        assertThat(getProductResponseForTesting(initialProduct.getId()).getIsDeleted()).isEqualTo(false);

        restTemplate.delete(createURLWithPort(productsUri + "/" + initialProduct.getId()));

        assertThat(getProductResponseForTesting(initialProduct.getId()).getIsDeleted()).isEqualTo(true);
    }


    //API error handling testing
    @Test
    public void createNewProductForBadInputShouldFail() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //sending in empty CreateProductRequest should fail
        HttpEntity<CreateProductRequest> request = new HttpEntity<>(new CreateProductRequest(), headers);

        ResponseEntity<GetProductResponse> response = restTemplate.postForEntity(createURLWithPort(productsUri), request,
                GetProductResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getNonExistingProductShouldGiveNotFoundResponse() {
        //given:
        //set up product for testing
        GetProductResponse initialProduct = createNewProductForTesting();
        assertThat(initialProduct).isNotNull();
        assertThat(initialProduct.getId()).isPositive();

        //ask for non existing product
        String url = createURLWithPort(productsUri + "/" + initialProduct.getId()+1);
        ResponseEntity<GetProductResponse> response = restTemplate.getForEntity(url, GetProductResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
