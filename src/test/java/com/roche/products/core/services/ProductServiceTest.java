package com.roche.products.core.services;

import com.roche.RocheProductsApplicationTestBase;
import com.roche.products.common.request.CreateProductRequest;
import com.roche.products.core.model.Product;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTest extends RocheProductsApplicationTestBase {

    @Test
    public void createProductMethodsValidAnnotation_ShouldValidateParam(){
        assertThrows(ConstraintViolationException.class, () -> productService.createProduct(new CreateProductRequest()));
    }

    @Test
    public void createProduct_shouldGiveExceptionOnNullInput(){
        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(null));
    }

    @Test
    public void updateProductMethodsValidAnnotation_ShouldValidateParam(){
        assertThrows(ConstraintViolationException.class, () -> productService.updateProduct(new Product()));
    }

    @Test
    public void updateProduct_shouldGiveExceptionOnNullInput(){
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(null));
    }
}
