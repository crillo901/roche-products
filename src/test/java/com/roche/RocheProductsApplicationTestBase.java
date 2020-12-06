package com.roche;

import com.roche.products.core.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
public class RocheProductsApplicationTestBase {

    public TestRestTemplate restTemplate = new TestRestTemplate();
    public String productsUri = UriComponentsBuilder.fromUriString("/products").build().toUriString();

    public String createURLWithPort(String uri) {
        return "http://localhost:" + randomServerPort + uri;
    }

    @BeforeEach
    public void init() { }

    @LocalServerPort
    protected int randomServerPort;

    @Autowired
    public ProductService productService;


}
