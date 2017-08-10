package com.core.security;


import com.core.exception.GatewayAuthorizationException;
import com.core.exception.GatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MicroAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebResponseExceptionTranslator exceptionTranslator = new DefaultWebResponseExceptionTranslator();
    private ExceptionRenderer exceptionRenderer = new ExceptionRenderer();

    public MicroAuthenticationEntryPoint() {

    }

    public MicroAuthenticationEntryPoint(ExceptionRenderer exceptionRenderer) {
        this.exceptionRenderer = exceptionRenderer;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        GatewayException gatewayException = null;
        try {
            ResponseEntity<OAuth2Exception> result = exceptionTranslator.translate(authException);
            gatewayException = new GatewayAuthorizationException(result.getBody().getMessage());
        } catch (Exception e) {
            gatewayException = new GatewayAuthorizationException();
        } finally {
            logger.error("授权失败", authException);
            exceptionRenderer.handleHttpEntityResponse(response, gatewayException, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
