package com.core.exception;


public class GatewayAuthorizationException extends GatewayException {

    public GatewayAuthorizationException(String message, Throwable cause) {
        super(message, cause);
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.INVALID_AUTHENTICATION));
    }

    /**
     * @param
     */
    public GatewayAuthorizationException() {
        super(ErrorHolder.getMessage(ErrorHolder.CodeTemp.INVALID_AUTHENTICATION, ErrorHolder.CodeTemp.INVALID_AUTHENTICATION.getTemp()));
        this.setErrorCode(ErrorHolder.getCode(ErrorHolder.CodeTemp.INVALID_AUTHENTICATION));
    }

    /**
     * @param message
     */
    public GatewayAuthorizationException(String message) {
        super(message, ErrorHolder.CodeTemp.INVALID_AUTHENTICATION.getCode());
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     */
    public GatewayAuthorizationException(String message, String errorCode) {
        super(message, errorCode);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param errorCode
     * @param cause
     */
    public GatewayAuthorizationException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
        // TODO Auto-generated constructor stub
    }
}
