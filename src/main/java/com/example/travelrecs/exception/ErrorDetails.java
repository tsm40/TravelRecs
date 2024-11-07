package com.example.travelrecs.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    private String error;
    private String message;

    public ErrorDetails(String error, String message) {
        this.error = error;
        this.message = message;
    }
}