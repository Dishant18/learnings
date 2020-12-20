package com.explore.learnings.service;

import com.explore.learnings.model.User;
import com.explore.learnings.model.req.SignUpReq;
import com.explore.learnings.repository.UserDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDB userDB;

    public Mono<User> upsertUser(SignUpReq signUp) {
        return userDB.upsertUser(signUp);
    }
}
