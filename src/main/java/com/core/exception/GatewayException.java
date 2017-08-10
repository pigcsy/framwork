package com.core.exception;



import java.util.ArrayList;
import java.util.List;

public class GatewayException extends RuntimeException {

    private static final long serialVersionUID = -360277845666981697L;

    private String errorCode = ErrorHolder.CodeTemp.UNKNOW.getCode();

    private List<GatewayException> relationExceptions;


    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void addExceptions(GatewayException exception) {
        relationExceptions = relationExceptions == null ? new ArrayList<>() : relationExceptions;
        relationExceptions.add(exception);
    }

}
