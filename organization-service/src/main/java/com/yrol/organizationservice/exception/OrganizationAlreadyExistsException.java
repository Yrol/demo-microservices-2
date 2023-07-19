package com.yrol.organizationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom runtime exception class when organization already exists
 * */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OrganizationAlreadyExistsException extends RuntimeException {

    private String organization;

    public OrganizationAlreadyExistsException(String organization) {
        super(String.format("%s has already been taken.", organization));
        this.organization = organization;
    }
}
