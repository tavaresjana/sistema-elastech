package com.soulcode.sistemachamadosdois.service;


import com.soulcode.sistemachamadosdois.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // This should be hashed, not the plain password
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        String role = user.getRole().getName();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (role != null && !role.isEmpty()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming accounts never expire in your application
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming accounts are never locked in your application
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You might implement logic to check for password expiration here
    }

    @Override
    public boolean isEnabled() {
        return user.isActive(); // Assuming enabled status is based on client.isActive()
    }
}
