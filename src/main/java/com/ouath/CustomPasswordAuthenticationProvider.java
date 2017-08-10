package com.ouath;


import com.common.constant.SystemEn;
import com.core.exception.GatewayException;
import com.core.security.DefaultCurrentPrincipal;
import com.core.util.AppUtils;
import com.core.util.RSAUtils;
import com.domain.oauth.AdminUserVo;
import com.domain.oauth.OauthSystemVo;
import com.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomPasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    OauthService oauthService;
    @Autowired
    private CustomUserDetailService userDetailsService;
    @Value("${spring.rsa.privateKey}")
    private String rsaPrivateKey;

    private GrantedAuthoritiesMapper authoritiesMapper = new SimpleAuthorityMapper();

    public CustomPasswordAuthenticationProvider() {
        this.setHideUserNotFoundExceptions(false);
    }

    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        try {
            if (authentication.getCredentials() == null) {
                logger.debug("- failed: no credentials provided");
                throw new GatewayException("密码不能为空");
            }
            String rawPass = authentication.getCredentials().toString();
            String md5Pwd = AppUtils.md5Encrypt(RSAUtils.decryptByPrivateKey(rawPass, rsaPrivateKey));
            // String md5Pwd = AppUtils.md5Encrypt(rawPass);
            if (!md5Pwd.equalsIgnoreCase(userDetails.getPassword())) {
                throw new GatewayException("密码错误");
            }
        } catch (Exception e) {
            throw new BadCredentialsException("密码错误", e);
        }
    }

    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        UserDetails loadedUser;

        try {

            loadedUser = userDetailsService.loadUserByUsername(username, authentication);
        } catch (UsernameNotFoundException notFound) {
            throw notFound;
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException("未知错误", repositoryProblem);
        }
        return loadedUser;
    }

    @SuppressWarnings("unchecked")
    /**
     * 调用system 获取用户信息
     */
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        String clientId = ((Map<String, Object>) authentication.getDetails()).get("client_id").toString();
        AdminUserVo systemUser = oauthService.queryByUserName(user.getUsername());
        List<String> authoritys = oauthService.queryRoleByUserId(systemUser.getUserId());
        OauthSystemVo oauthSystemVo = oauthService.queryDetailsByClientId(clientId);

        DefaultCurrentPrincipal currentUser = new DefaultCurrentPrincipal(systemUser.getUserId(), systemUser.getUserName(),
                systemUser.getSystemId(), oauthSystemVo.getSystemType(), systemUser.getDailyAccessNum(), systemUser.getMinuteAccessNum());
        SystemEn systemEn = SystemEn.toEnum(oauthSystemVo.getSystemType());
        List<GrantedAuthority> authorityList = authoritys.stream()
                .map(authority -> new SimpleGrantedAuthority(systemEn.getRolePrefix() + authority))
                .collect(Collectors.toList());
        Collection<? extends GrantedAuthority> authorities = authoritiesMapper.mapAuthorities(authorityList);

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(currentUser,
                authentication.getCredentials(), authorities);
        result.setDetails(authentication.getDetails());
        return result;
    }


}
