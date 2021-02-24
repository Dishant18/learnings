package com.explore.learnings.utils;

public interface Constants {

    interface ColumnConstants {
        String COL_EMAIL = "email";
        String COL_FNAME = "first_name";
        String COL_LNAME = "last_name";
        String COL_UID = "user_id";
        String COL_PWD = "password";
        String COL_TOKEN = "token";
        String COL_EXP = "expiry";
    }

    interface TableConstants {
        String TABLE_USER = "learning_db.user";
        String TABLE_TOKEN = "learning_db.token";

    }
}
