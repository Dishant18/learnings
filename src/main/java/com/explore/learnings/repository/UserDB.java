package com.explore.learnings.repository;

import com.explore.learnings.model.User;
import com.explore.learnings.model.req.SignUpReq;
import com.explore.learnings.utils.DBMappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static com.explore.learnings.utils.Constants.ColumnConstants.*;
import static com.explore.learnings.utils.Constants.TableConstants.TABLE_USER;
import static com.explore.learnings.utils.SQLQueryBuilder.upsertQuery;
import static java.util.Arrays.asList;
import static java.util.Objects.isNull;

@Repository
@Slf4j
public class UserDB {

    @Autowired
    private DatabaseClient databaseClient;

    public Mono<User> upsertUser(SignUpReq user) {
        /**
         * SignUp req can never have null values. For adding support to upsert, should we add checks or create a new obj :think:
         * TODO: Need to make a new token if not present!
         */
        log.info("Upserting user with email: {}", user.email());
        List<String> updValues = new ArrayList<>();
        if (!isNull(user.email())) updValues.add(COL_EMAIL);
        if (!isNull(user.firstName())) updValues.add(COL_FNAME);
        if (!isNull(user.lastName())) updValues.add(COL_LNAME);
        if (!isNull(user.pwd())) updValues.add(COL_PWD);
        return databaseClient.execute(upsertQuery(TABLE_USER, asList(COL_EMAIL, COL_FNAME, COL_LNAME, COL_PWD), updValues))
                .bind(COL_EMAIL, user.email())
                .bind(COL_FNAME, user.firstName())
                .bind(COL_LNAME, user.lastName())
                .bind(COL_PWD, user.pwd())
                .map(DBMappers::RM_USER)
                .one();
    }
}
