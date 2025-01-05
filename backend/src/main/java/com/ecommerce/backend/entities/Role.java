package com.ecommerce.backend.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

//    @Override
//    public java.lang.String getAuthority() {
//        // User'ın rolüne göre authorities oluşturuyoruz
//        // Bu enum elemanının adı üzerinden authorities oluşturuyoruz
//        return new SimpleGrantedAuthority(this.name()).getAuthority();
//    }

    @Override
    public String getAuthority() {
        return name();
    }
}
