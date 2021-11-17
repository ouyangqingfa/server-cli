package com.ad.core.system.auth.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author CoderYoung
 */
public class JwtToken implements AuthenticationToken {
    /**
     * Token
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
