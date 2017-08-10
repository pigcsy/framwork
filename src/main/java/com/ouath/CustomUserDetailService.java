package com.ouath;

import com.core.exception.GatewayException;
import com.domain.oauth.AdminUserVo;
import com.domain.oauth.OauthSystemVo;
import com.service.OauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Map;

@Component
public class CustomUserDetailService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    DataSource dataSource;
    @Autowired
    OauthService oauthService;

    public CustomUserDetailService() {
    }

    // AuthorizationClientTest authorizationClient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("未找到用户");
    }

    @SuppressWarnings("unchecked")
    public UserDetails loadUserByUsername(String username, Authentication authentication)
            throws UsernameNotFoundException {
        logger.info("用户权限请求,username={}", username);
        ;
        try {
            String clientId = ((Map<String, Object>) authentication.getDetails()).get("client_id").toString();
            AdminUserVo staff =oauthService.queryByUserName(username);
            OauthSystemVo oauthSystemVo =oauthService.queryDetailsByClientId(clientId);
            if (staff.getSystemId() != oauthSystemVo.getSystemId().intValue()) {
                throw new GatewayException("未找到用户");
            }
            return new User(username, staff.getPwd(), staff.getStatus() != 0, true, true, true,
                    new ArrayList<GrantedAuthority>());
        } catch (Exception e) {
            logger.error("未找到用户->{}", username, e);
            throw new UsernameNotFoundException("用户不存", e);
        }

    }

}
