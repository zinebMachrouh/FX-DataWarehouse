package org.example.fxdatawarehouse.Exceptions;

// Exception to be thrown when a currency pattern is invalid

public class CurrencyPatternException extends RuntimeException {
    public CurrencyPatternException(String message) {
        super(message);
    }
}
