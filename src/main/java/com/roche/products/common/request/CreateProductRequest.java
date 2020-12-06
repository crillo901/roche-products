package com.roche.products.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateProductRequest implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private String sku;

    @NotNull
    private String name;

    @NotNull
    @PositiveOrZero
    private Integer priceInCents;

    @NotNull
    private Boolean isDeleted;
}
