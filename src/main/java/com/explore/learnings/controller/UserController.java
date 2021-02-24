package com.explore.learnings.controller;

import com.explore.learnings.model.User;
import com.explore.learnings.model.req.SignUpReq;
import com.explore.learnings.repository.UserDB;
import com.explore.learnings.service.TokenService;
import com.explore.learnings.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.explore.learnings.utils.Helper.GSON;
import static com.explore.learnings.utils.Helper.isValidEmail;
import static java.util.Arrays.asList;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDB userDB;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/signUp")
    public Mono<Void> signUp(@RequestBody SignUpReq signUp) {
        Mono<Void> userMono = userService.upsertUser(signUp);
        return userMono
                .doOnSuccess(__ -> {
                    log.info("Executing get user");
                    userService.getUserByEmail(signUp.email())
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .flatMap(user -> {
                                log.info("Executing token thingy");
                                return tokenService.insertTokenIfNotPresent(user.userId())
                                        .log();
                            })
                            .subscribe();
                });
    }

    @GetMapping(value = "/learn")
    public Mono<Void> learn() {
        Flux<String> stringFlux = Flux.just("hi", "yo", "fancy", "one more");
        return stringFlux
                //.map(String::toUpperCase)
                .doOnNext(word -> {
                    System.out.println("Sleeping " + word);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Waking up " + word);
                })
                .doOnNext(word -> {
                    System.out.println("Current: " + word);
                })
                .doOnComplete(() -> {
                    System.out.println("Stuck in middle");
                })
                .doOnNext(word -> {
                    System.out.println("Sleeping-2 " + word);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Waking up-2 " + word);
                })
                .doOnComplete(() -> {
                    System.out.println("I am Dishant");
                })
                //.log()
                .then();
    }

    @GetMapping(value = "/learn1")
    public Mono<Map<String, Object>> learn1(@RequestParam String email) {
//        List<String> emails = asList("dishantt@flock.com", "dishantt+test@flock.com");
//        return getUsers(emails).collectList()
//                .map(users -> {
//                    //Insert AG - Mono<AG>
//                    return Mono.just(1)
//                            .map(alertGroup -> )
//                }
////                .doOnComplete(() -> {
////                    getUserAvi(emails.get(0))
////                            .doOnNext(user -> {
////                                log.info("Inside ON NEXT");
////                                SignUpReq signUpReq = new SignUpReq(user.email(), user.firstName(), user.lastName(), "cheanged pwd");
////                                userService.upsertUser(signUpReq);
////                            })
////                            .doOnSuccess(__ -> {
////                                users.forEach(user -> {
////                                    log.info("User from list: {}", GSON.toJson(user));
////                                });
////                            });
////                })
////                .log()
////                .then();

        log.info("HIT");
        SignUpReq signUpReq = new SignUpReq(email, "D", "T", "cheanged pwd");
        Mono<Map<String, Object>> user = userDB.insertUser(signUpReq);
        log.info("Returning now: {}", GSON.toJson(user));
        user.map(mp -> {
            log.info("mp: {}", GSON.toJson(mp));
            return mp.get("id");
        }).subscribe();
        return user;
    }

    public Flux<User> getUsers(List<String> emails) {
        return Flux.fromIterable(emails)
                .flatMap(this::getUserAvi);
    }

    public Mono<User> getUserAvi(String email) {
        return userService.getUser(email);
    }

//    @GetMapping(value = "/user/{identifier}")
//    public Mono<ResponseEntity> getUser(@PathVariable String identifier) {
//        Mono<Optional<User>> userMono;
//        if (isValidEmail(identifier)) {
//            userMono = userService.getUserByEmail(identifier);
//        } else {
//            userMono = userService.getUserById(identifier);
//        }
//        return userMono
//                .map(optionalUser -> {
//                    if (optionalUser.isPresent()) {
//                        return ResponseEntity.ok(optionalUser.get());
//                    } else {
//                        return ResponseEntity.notFound();
//                    }
//                })
//    }
}