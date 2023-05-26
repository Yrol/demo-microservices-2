package com.yrol.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom runtime exception class when department already exists
 * */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentAlreadyExistsException extends RuntimeException {

    private String department;

    public DepartmentAlreadyExistsException(String department) {
        super(String.format("%s has already been taken.", department));
        this.department = department;
    }
}
