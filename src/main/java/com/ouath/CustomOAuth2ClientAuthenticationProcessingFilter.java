package com.ouath;

import com.alibaba.fastjson.JSON;
import com.core.base.DefaultResult;
import com.core.exception.GatewayException;
import com.core.util.HttpErrorDecoder;
import com.core.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomOAuth2ClientAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

	public OAuth2RestOperations restTemplate;

	private OAuth2ClientContext context;

	public OAuth2RestOperations getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(OAuth2RestOperations restTemplate) {
		this.restTemplate = restTemplate;
	}

	public OAuth2ClientContext getContext() {
		return context;
	}

	public void setContext(OAuth2ClientContext context) {
		this.context = context;
	}

	public CustomOAuth2ClientAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (!requiresAuthentication(request, response)) {
			chain.doFilter(request, response);
			return;
		}
		DefaultResult<?> result = null;
		try {
			OAuth2AccessToken accessToken = restTemplate.getAccessToken();
			Map<String, Object> map = new HashMap<>();
			map.put("tokenType", accessToken.getTokenType());
			map.put("token", accessToken.getValue());
			map.put("expiration", accessToken.getExpiration());
			result = new DefaultResult<>(map);
		} catch (Exception e) {
			logger.error("认证失败", e);
			GatewayException exception = null;
			if (e.getCause() != null && e.getCause() instanceof OAuth2Exception) {
				OAuth2Exception fail = (OAuth2Exception) e.getCause();
				if (fail != null && !fail.getAdditionalInformation().isEmpty()) {
					exception = HttpErrorDecoder.decode(HttpStatus.INTERNAL_SERVER_ERROR,
							JSON.toJSONString(fail.getAdditionalInformation()));
				} else {
					exception = new GatewayException("认证失败");
				}
			} else {
				exception = new GatewayException("认证失败");
			}
			result = new DefaultResult<>(exception.getMessage(), exception.getErrorCode());
		} finally {
			context.setAccessToken(null);
		}
		WebUtils.sendJson(response, JSON.toJSONString(result));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		return null;
	}

	@Override
	public void afterPropertiesSet() {
	}

}
