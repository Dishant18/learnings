package com.explore.learnings.utils;

import com.explore.learnings.model.User;
import io.r2dbc.spi.Row;

import static com.explore.learnings.utils.Constants.ColumnConstants.*;

public class DBMappers {

    public static User RM_USER(Row rs) {
        return new User()
                .email(rs.get(COL_EMAIL, String.class))
                .firstName(rs.get(COL_FNAME, String.class))
                .lastName(rs.get(COL_LNAME, String.class))
                .userId(rs.get(COL_UID, Integer.class));
    }
}
