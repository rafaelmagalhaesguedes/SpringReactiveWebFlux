package com.web.flux.services.exceptions;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException() {
        super("Ops! Product not found.");
    }
}
