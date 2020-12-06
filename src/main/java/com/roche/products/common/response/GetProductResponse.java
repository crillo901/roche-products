package com.roche.products.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponse {
    private Long id;
    private String sku;
    private String name;
    private Boolean isDeleted;
    private Integer priceInCents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
