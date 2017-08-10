package com.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GatewayExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GatewayExceptionHandler.class);
    private ErrorAttributes errorAttributes = new DefaultErrorAttributes();

    /**
     * 处理自定义异常
     *
     * @param throwable
     * @param request
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @ExceptionHandler(value = {GatewayException.class})
    public final ResponseEntity<ErrorResult> handleGatewayException(GatewayException throwable,
                                                                  HttpServletRequest request) {
        logger.error("", throwable);
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        errorAttributes.getErrorAttributes(requestAttributes, false).get("javax.servlet.error.status_code");
        return new ResponseEntity<ErrorResult>(new ErrorResult(throwable.getClass().getName(), throwable.getMessage(), throwable.getErrorCode()), new HttpHeaders(), HttpStatus.OK);

    }


}