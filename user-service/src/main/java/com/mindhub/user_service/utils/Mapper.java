package com.mindhub.user_service.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Mapper {

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
