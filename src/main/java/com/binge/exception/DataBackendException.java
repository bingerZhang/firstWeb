package com.binge.exception;

import java.io.IOException;

public class DataBackendException extends IOException {
    public DataBackendException() {
    }

    public DataBackendException(String message) {
        super(message);
    }

    public DataBackendException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBackendException(Throwable cause) {
        super(cause);
    }
}