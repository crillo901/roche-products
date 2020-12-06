package com.roche.products.api.rest;

import com.roche.products.common.request.CreateProductRequest;
import com.roche.products.common.response.GetProductListResponse;
import com.roche.products.common.response.GetProductResponse;
import com.roche.products.core.model.Product;
import com.roche.products.core.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductResource.class);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<GetProductResponse> create(@Valid @RequestBody CreateProductRequest createProductRequest) {

        logger.info("Request received for creating {} product", createProductRequest);

        Product product = productService.createProduct(createProductRequest);

        GetProductResponse getProductResponse = createGetProductResponseFromProduct(product);

        logger.info("Product created: {}", getProductResponse);

        return new ResponseEntity<>(getProductResponse, HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<GetProductListResponse> list() {

        logger.info("Request received for listing products");

        List<Product> products = productService.findAll();
        List<GetProductResponse> getProductResponses = new ArrayList<>();

        products.forEach(product -> getProductResponses.add(createGetProductResponseFromProduct(product)));

        GetProductListResponse getProductListResponse = new GetProductListResponse(getProductResponses);

        logger.info("Found products: {}", getProductListResponse.getResponseList().size());

        return new ResponseEntity<>(getProductListResponse, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<GetProductResponse> getProductById(@PathVariable Long id) {

        logger.info("Request received for getting product by id: {}", id);

        Product product = productService.getById(id);

        GetProductResponse getProductResponse = createGetProductResponseFromProduct(product);

        logger.info("Found product: {} ", getProductResponse);

        return new ResponseEntity<>(getProductResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<GetProductResponse> delete(@PathVariable Long id) {

        logger.info("Request received for deleting product by id: {}", id);

        Product deletedProduct = productService.deleteProduct(id);

        GetProductResponse getProductResponse = createGetProductResponseFromProduct(deletedProduct);

        logger.info("deleted product: {} ", getProductResponse);

        return new ResponseEntity<>(getProductResponse, HttpStatus.OK);
    }

    @PatchMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<GetProductResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody CreateProductRequest createProductRequest) {

        logger.info("Request received for update product: {}", id);

        Product product = productService.getById(id);

        if (!product.getName().equals(createProductRequest.getName())) {
            product.setName(createProductRequest.getName());
        }
        if (!product.getPriceInCents().equals(createProductRequest.getPriceInCents())) {
            product.setPriceInCents(createProductRequest.getPriceInCents());
        }

        product = productService.updateProduct(product);

        GetProductResponse getProductResponse = createGetProductResponseFromProduct(product);


        logger.info("updated product: {} ", getProductResponse);

        return new ResponseEntity<>(getProductResponse, HttpStatus.OK);
    }

    public static GetProductResponse createGetProductResponseFromProduct(Product product) {
        return new GetProductResponse(product.getId(), product.getSku(), product.getName(),
                product.getDeleted(), product.getPriceInCents(), product.getCreatedAt(), product.getUpdatedAt());
    }
}
