package org.wonderbeat.transfer.service;

public class OwerflowException extends RuntimeException {

    public OwerflowException() {
    }

    public OwerflowException(String message) {
        super(message);
    }

    public OwerflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public OwerflowException(Throwable cause) {
        super(cause);
    }

    public OwerflowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
