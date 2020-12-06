package com.roche.products.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {

    public ErrorResponse(String error, String type) {
        this.error = error;
        this.type = type;
    }

    private String error;
    private String type;
    private Map<String, String> mapOfValidationErrors;
}
