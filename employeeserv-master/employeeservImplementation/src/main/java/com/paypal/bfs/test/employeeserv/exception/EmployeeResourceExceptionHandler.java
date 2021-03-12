package com.paypal.bfs.test.employeeserv.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@RestControllerAdvice
public class EmployeeResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleAllExceptions(Exception ex,WebRequest request) {
        log.error("Error in handleAllExceptions: ",ex);
        return new EmployeeServApiError(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,request).buildResponseEntity();
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<?> handleResponseException(ResponseStatusException rsException, WebRequest request) {
        log.error("Error in handleResponseException: ",rsException);
        return new EmployeeServApiError(rsException.getReason(),rsException.getStatus(),request).buildResponseEntity();
    }
}
