package com.cse.haste.context;

/**
 * @author WangZhenqi
 */
public class HasteException extends RuntimeException {
    private int statusCode;

    public HasteException(int statusCode) {
        this.statusCode = statusCode;
    }

    public HasteException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public HasteException(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
