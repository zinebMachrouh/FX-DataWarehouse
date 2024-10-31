package org.example.fxdatawarehouse.Utils;

import java.util.regex.Pattern;

public class CurrencyValidator {
    public static boolean isValidCurrency(String currency) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        return pattern.matcher(currency).matches();
    }
}
