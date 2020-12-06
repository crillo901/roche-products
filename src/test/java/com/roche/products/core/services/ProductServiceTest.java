package com.roche.products.core.services;

import com.roche.RocheProductsApplicationTestBase;
import com.roche.products.common.request.CreateProductRequest;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTest extends RocheProductsApplicationTestBase {

    @Test
    public void validAnnotationShouldValidateParam(){
        assertThrows(ConstraintViolationException.class, () -> productService.createProduct(new CreateProductRequest()));
    }
}
