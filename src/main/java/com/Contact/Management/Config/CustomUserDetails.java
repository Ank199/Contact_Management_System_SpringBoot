package com.Contact.Management.Config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Contact.Management.Models.User;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Customize the logic according to your application's requirement
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Customize the logic according to your application's requirement
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Customize the logic according to your application's requirement
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Customize the logic according to your application's requirement
        return true;
    }
}