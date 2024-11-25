package com.web.flux.services.exceptions;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException() {
        super("Ops! Category not found.");
    }
}
