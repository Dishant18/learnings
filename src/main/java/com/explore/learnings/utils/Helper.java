package com.explore.learnings.utils;

import com.google.gson.Gson;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Helper {

    public static final Gson GSON = new Gson();

    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String pwd) {
        return passwordEncoder.encode(pwd);
    }
}
