package com.codebeast.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Record already exists")
public class NoDuplicatesAllowedException extends Exception {
}
