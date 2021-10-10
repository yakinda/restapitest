package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseObject {
    private String status;
    private String message;
    private Object data;
}
