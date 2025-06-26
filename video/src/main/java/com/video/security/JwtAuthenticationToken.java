package com.video.security;

import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String email;
    private final String role;

    public JwtAuthenticationToken(String email, String role) {
        super(List.of(new SimpleGrantedAuthority("ROLE_" + role)));
        this.email = email;
        this.role = role;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null; // no credentials stored here
    }

    @Override
    public Object getPrincipal() {
        return email;
    }
}
