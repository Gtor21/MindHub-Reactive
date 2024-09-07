package com.mindhub.user_service.dtos;

import org.springframework.security.core.userdetails.UserDetails;

public class AuthUser {

    private String email;
    private String password;
    private UserDetails principal;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails getPrincipal() {
        return principal;
    }

    public void setPrincipal(UserDetails principal) {
        this.principal = principal;
    }
}
