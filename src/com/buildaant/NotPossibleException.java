package com.buildaant;

class NotPossibleException extends Exception {
    public NotPossibleException() {
    }

    public NotPossibleException(String message) {
        super(message);
    }

    public NotPossibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotPossibleException(Throwable cause) {
        super(cause);
    }
}