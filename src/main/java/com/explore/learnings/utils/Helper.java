package com.explore.learnings.utils;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static final Gson GSON = new Gson();
    public static final Integer TOKEN_SIZE = 32;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

//    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public static String encryptPassword(String pwd) {
//        return passwordEncoder.encode(pwd);
//    }

    public static String generateToken() {
        return RandomStringUtils.randomAlphanumeric(TOKEN_SIZE);
    }

    public static boolean isValidEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
