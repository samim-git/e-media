package com.sam.emedia.gateway.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class TokenBaseAuthentication extends AbstractAuthenticationToken {
    private String token;
    private final UserDetails principle;

    public TokenBaseAuthentication(UserDetails principle ) {
        super( principle.getAuthorities() );
        this.principle = principle;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }
}
