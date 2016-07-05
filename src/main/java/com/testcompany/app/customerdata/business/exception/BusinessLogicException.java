package com.testcompany.app.customerdata.business.exception;

public class BusinessLogicException extends RuntimeException {

    private final int errorCode;

    public BusinessLogicException(final int errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessLogicException(final int errorCode, final Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BusinessLogicException(final int errorCode, final String message, final Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
