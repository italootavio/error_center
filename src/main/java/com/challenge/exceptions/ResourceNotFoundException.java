package com.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "element not found")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName) {
        super("Resource: " + resourceName + " not found");
    }
}