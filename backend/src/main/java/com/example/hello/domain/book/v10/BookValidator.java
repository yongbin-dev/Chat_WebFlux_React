package com.example.hello.domain.book.v10;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Slf4j
@Component("bookValidatorV10")
public class BookValidator<T> {
    private final Validator validator;

    public BookValidator(Validator validator) {
        this.validator = validator;
    }

    public void validate(T body) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(body);
        if (!constraintViolations.isEmpty()) {
            onValidationErrors(constraintViolations);
        }
    }

    private void onValidationErrors(Set<ConstraintViolation<T>> constraintViolations) {
        log.error(constraintViolations.toString());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                            constraintViolations.toString());
    }
}
