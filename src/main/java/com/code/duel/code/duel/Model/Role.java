package com.code.duel.code.duel.Model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, PLAYER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
