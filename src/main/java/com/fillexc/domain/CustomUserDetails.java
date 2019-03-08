package com.fillexc.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(final User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        if (user.getActive() == 1)
            return true;

        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (user.getActive() == 1)
            return true;

        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (user.getActive() == 1)
            return true;

        return false;
    }

    @Override
    public boolean isEnabled() {
        if (user.getActive() == 1)
            return true;

        return false;
    }
}
