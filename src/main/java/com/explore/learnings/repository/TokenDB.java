package com.explore.learnings.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Calendar;

import static com.explore.learnings.utils.Constants.ColumnConstants.*;
import static com.explore.learnings.utils.Constants.TableConstants.TABLE_TOKEN;
import static com.explore.learnings.utils.DBMappers.RM_AUTH_TOKEN;
import static com.explore.learnings.utils.SQLQueryBuilder.insertQuery;
import static com.explore.learnings.utils.SQLQueryBuilder.selectQuery;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static reactor.core.publisher.Mono.defer;

@Slf4j
@Repository
public class TokenDB {

    @Autowired
    private DatabaseClient databaseClient;

    public Mono<Void> insertTokenIfNotPresent(String token, int userId) {
        log.info("Checking if token is present or not for userId: {}", userId);
        return databaseClient.execute(selectQuery(TABLE_TOKEN, null, singletonList(COL_UID), true))
                .bind(COL_UID, userId)
                .map(RM_AUTH_TOKEN)
                .first()
                .switchIfEmpty(defer(() -> {
                    log.info("No authToken present.. Preparing to insert token");
                    databaseClient.execute(insertQuery(TABLE_TOKEN, asList(COL_UID, COL_EXP, COL_TOKEN)))
                            .bind(COL_UID, userId)
                            .bind(COL_TOKEN, token)
                            .bind(COL_EXP, getExpiryDate())
                            .then()
                            .log()
                            .subscribe();
                    return Mono.empty();
                }))
                .then()
                .log();
    }

    private long getExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTimeInMillis();
    }

}
