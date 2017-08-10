package com.core.security;

import com.alibaba.fastjson.JSON;
import com.core.exception.ErrorResult;
import com.core.exception.GatewayException;
import com.core.util.WebUtils;
import com.domain.System.DefaultResult;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

public class ExceptionRenderer {
    // 是否为传递异常
    boolean exceptionTransmit = true;
    ;

    public ExceptionRenderer(boolean exceptionTransmit) {
        this.exceptionTransmit = exceptionTransmit;
    }

    public ExceptionRenderer() {
    }

    public void handleHttpEntityResponse(HttpServletResponse response, GatewayException gatewayException, HttpStatus httpStatus) {
        if (exceptionTransmit) {
            WebUtils.sendJson(response, JSON.toJSONString(new ErrorResult(gatewayException, httpStatus)),
                    HttpStatus.OK);
        } else {
            WebUtils.sendJson(response,
                    JSON.toJSONString(new DefaultResult<>(gatewayException.getMessage(), gatewayException.getErrorCode())),
                    httpStatus);
        }

    }
}
