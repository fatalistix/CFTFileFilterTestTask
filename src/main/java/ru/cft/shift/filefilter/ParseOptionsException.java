package ru.cft.shift.filefilter;

// ParseOptionsException class is created to divide dependencies: only FilterOptionsParser depends on apache's
// common-cli library
public class ParseOptionsException extends Exception {
    public ParseOptionsException(Throwable cause) {
        super(cause);
    }

    public ParseOptionsException(String message) {
        super(message);
    }
}
