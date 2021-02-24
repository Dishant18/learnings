package com.explore.learnings.service;

import com.explore.learnings.repository.TokenDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.explore.learnings.utils.Helper.generateToken;

@Slf4j
@Service
public class TokenService {

    @Autowired
    private TokenDB tokenDB;

    public Mono<Void> insertTokenIfNotPresent(int userId) {
        return tokenDB.insertTokenIfNotPresent(generateToken(), userId);
    }
}
