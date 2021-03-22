package com.explore.learnings.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class LearnController {

    private static final ExecutorService es = newFixedThreadPool(10, new ThreadFactoryBuilder().setNameFormat("alert-processor-%d").build());
    private static final ExecutorService es2 = newFixedThreadPool(10, new ThreadFactoryBuilder().setNameFormat("alert-notifier-%d").build());

    public static void main(String[] args) throws InterruptedException {
        es.execute(() -> go(1));
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MINUTES);
        es2.shutdown();
    }

    private static void go(int i) {
        System.out.println("Inside method go: " + i + " thread: " + Thread.currentThread().getName());
//        int st = i * 10;
//        for (int j = st; j < st + 3; j++) {
//            int k = j;
//            es2.execute(() -> sleepAndReturn(k));
//        }
        for (int id = i; id <= i + 5; id++) {
            int finalId = id;
            es2.execute(() -> sleepAndReturn(finalId));
        }
        System.out.println("Returning from method go: " + i + " thread: " + Thread.currentThread().getName());
    }

    private static CompletableFuture<String> sleepAndReturn(int id) {
        System.out.println("Inside method SAR: " + id + " thread: " + Thread.currentThread().getName());
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Returning method SAR: " + id + " thread: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture("Returning " + id);
    }
}
