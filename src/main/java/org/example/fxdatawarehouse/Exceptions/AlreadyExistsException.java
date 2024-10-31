package org.example.fxdatawarehouse.Exceptions;

import lombok.NoArgsConstructor;

// Exception to be thrown when a resource already exists

@NoArgsConstructor
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
