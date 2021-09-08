package com.crm.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private String errorMessage;
    private String errorCode;
    private String url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public Error(String errorMessage, String errorCode, String url) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.url = url;
    }

}
