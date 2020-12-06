package com.roche.products.core.services;

import com.roche.products.common.request.CreateProductRequest;
import com.roche.products.core.model.Product;

import javax.validation.Valid;
import java.util.List;

public interface ProductService {

    Product createProduct(@Valid CreateProductRequest createProductRequest);

    List<Product> findAll();

    Product getById(Long id);

    Product updateProduct(@Valid Product product);

    Product deleteProduct(Long id);
}
