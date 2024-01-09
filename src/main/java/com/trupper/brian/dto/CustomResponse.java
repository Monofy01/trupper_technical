package com.trupper.brian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponse {
    private String message;
    private Boolean success;
    private Object body;
}
