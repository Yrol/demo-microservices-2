package com.yrol.employeeservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom runtime exception class when employee already exists by email
 * */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyExistException extends RuntimeException {

    private String email;

    public EmployeeAlreadyExistException(String email) {
        super(String.format("%s has already been taken.", email));
        this.email = email;
    }
}
