package com.security.client.userOauth;


import com.core.security.DefaultResourceServerConfiguration;
import com.core.security.ExceptionRenderer;
import com.core.security.MicroAuthenticationEntryPoint;
import com.security.client.userSecurity.SecurityInterceptor;
import com.security.client.userSecurity.SecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableResourceServer
@Configuration
public class CustomResourceServerConfiguration extends DefaultResourceServerConfiguration {

    SecurityInterceptor securityInterceptor;
    @Autowired
    SecurityMetadataSource securityMetadataSource;

    @Value("${security.oauth2.resource.name}")
    private String resourceId;

    @Bean(name = "adminOauth2ClientConfig")
    @Primary
    @ConfigurationProperties(ignoreUnknownFields = false, prefix = "security.oauth2")
    public Oauth2ClientConfig getAdminOauth2ClientConfig() {
        return new Oauth2ClientConfig();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId);
        super.configure(resources);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        securityInterceptor = new SecurityInterceptor(securityMetadataSource);
        http.addFilterAfter(securityInterceptor, FilterSecurityInterceptor.class);

        http.httpBasic().authenticationEntryPoint(new MicroAuthenticationEntryPoint(new ExceptionRenderer(false)));
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers("/login", "/token", "/OPTIONS")
                .permitAll().anyRequest().authenticated().and().csrf().disable();
    }


}