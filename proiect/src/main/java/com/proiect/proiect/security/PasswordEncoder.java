package com.proiect.proiect.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String parola = bCryptPasswordEncoder.encode("1234");
        System.out.println(parola);
    }

}
