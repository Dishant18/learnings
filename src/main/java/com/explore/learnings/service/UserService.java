package com.explore.learnings.service;

import com.explore.learnings.model.User;
import com.explore.learnings.model.req.SignUpReq;
import com.explore.learnings.repository.UserDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static com.explore.learnings.utils.Constants.ColumnConstants.COL_EMAIL;
import static com.explore.learnings.utils.Constants.ColumnConstants.COL_UID;
import static reactor.core.publisher.Mono.defer;
import static reactor.core.publisher.Mono.error;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDB userDB;

    public Mono<Void> upsertUser(SignUpReq signUp) {
        Mono<Void> voidMono = userDB.upsertUser(signUp);
        log.info("Returning from upsert user");
        return voidMono;
    }

    public Mono<Optional<User>> getUserByEmail(String email) {
        log.info("Preparing to fetch user by email: {}", email);
        return userDB.getUserByCol(COL_EMAIL, email);
    }

    public Mono<User> getUser(String email) {
        log.info("Preparing to fetch user by email: {}", email);
        return userDB.getUser(email);
    }

    public Mono<Optional<User>> getUserById(String id) {
        log.info("Preparing to fetch user by id: {}", id);
        return userDB.getUserByCol(COL_UID, id);
    }
}
