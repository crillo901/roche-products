package com.roche.products.core.services;

import com.roche.products.common.request.CreateProductRequest;
import com.roche.products.core.model.Product;
import com.roche.products.core.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    @Transactional
    public Product createProduct(@Valid CreateProductRequest createProductRequest) {

        logger.info("Create product request received {} ", createProductRequest);

        if (createProductRequest == null) {
            throw new IllegalArgumentException("createProductRequest can not be null");
        }
        Product product = new Product(0L,createProductRequest.getSku(),createProductRequest.getName(),
                createProductRequest.getPriceInCents(), createProductRequest.getIsDeleted());

        Product savedProduct = productRepository.saveAndFlush(product);

        logger.info("created a new product {}", product);
        return savedProduct;
    }

    @Override
    @Transactional
    public List<Product> findAll() {
        logger.info("findAll products");

        List<Product> products = productRepository.findAll();

        logger.info("found {} products", products.size());

        return products;
    }

    @Override
    @Transactional
    public Product getById(Long id) {
        logger.info("get Product by id: {}", id);

        if (id == null) {
            throw new IllegalArgumentException("id can not be null");
        }
        Product product = productRepository.getOne(id);

        logger.info("found product: {}", product);

        return product;
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        logger.info("update product request received {} ", product);

        if (product.getId() <= 0) {
            throw new IllegalArgumentException("id must be positive integer");
        }
        Product updatedProduct = productRepository.saveAndFlush(product);

        logger.info("successfully updated product {}", updatedProduct);

        return updatedProduct;
    }

    @Override
    @Transactional
    public Product deleteProduct(Long id) {
        logger.info("delete product request received for productId: {}", id);

        Product product = productRepository.getOne(id);
        product.setDeleted(true);
        Product deletedProduct = productRepository.saveAndFlush(product);

        logger.info("deleted product {}", deletedProduct);

        return deletedProduct;
    }
}
