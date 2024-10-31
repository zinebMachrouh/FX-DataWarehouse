package org.example.fxdatawarehouse.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
