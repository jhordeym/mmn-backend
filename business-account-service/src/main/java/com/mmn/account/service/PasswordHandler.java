package com.mmn.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHandler {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean match(String rawPass, String encodedPass) {
        return bCryptPasswordEncoder.matches(rawPass, encodedPass);
    }
}
