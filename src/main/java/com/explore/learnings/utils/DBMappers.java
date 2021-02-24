package com.explore.learnings.utils;

import com.explore.learnings.model.AuthToken;
import com.explore.learnings.model.User;
import io.r2dbc.spi.Row;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static com.explore.learnings.utils.Constants.ColumnConstants.*;
import static java.lang.String.valueOf;
import static java.util.Objects.isNull;

public class DBMappers {

    public static Function<Row, Optional<User>> RM_USER = rs -> {
        if (isNull(rs)) return Optional.empty();
        return Optional.of(new User()
                    .email(rs.get(COL_EMAIL, String.class))
                    .firstName(rs.get(COL_FNAME, String.class))
                    .lastName(rs.get(COL_LNAME, String.class))
                    .userId(rs.get(COL_UID, Integer.class)));
    };

    public static Function<Row, User> RM_US = rs -> new User()
            .email(rs.get(COL_EMAIL, String.class))
            .firstName(rs.get(COL_FNAME, String.class))
            .lastName(rs.get(COL_LNAME, String.class))
            .userId(rs.get(COL_UID, Integer.class));

    public static Function<Map<String, Object>, User> RM_US_MP = rs -> new User()
            .email(valueOf(rs.get(COL_EMAIL)))
            .firstName(valueOf(rs.get(COL_FNAME)))
            .lastName(valueOf(rs.get(COL_LNAME)))
            .userId(Integer.parseInt(valueOf(rs.get(COL_UID))));

    public static Function<Row, AuthToken> RM_AUTH_TOKEN = rs -> new AuthToken()
                .userId(rs.get(COL_UID, Integer.class))
                .expiry(rs.get(COL_EXP, Long.class))
                .token(rs.get(COL_TOKEN, String.class));
}
