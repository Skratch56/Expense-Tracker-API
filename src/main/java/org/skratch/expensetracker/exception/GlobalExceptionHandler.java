package org.skratch.expensetracker.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpenseTrackerException.class)
    public ResponseEntity<Object> handleExpenseTrackerException(ExpenseTrackerException ex) {
        log.error("Error while processing Expenses {}", ex.getMessage());
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(ex.getMessage(), httpStatus);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleExpenseTrackerException(EntityNotFoundException ex) {
        log.error("Error while updating Expenses {}", ex.getMessage());
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(ex.getMessage(), httpStatus);
    }
}
