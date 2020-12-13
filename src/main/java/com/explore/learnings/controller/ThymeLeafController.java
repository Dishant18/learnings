package com.explore.learnings.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
@Slf4j
public class ThymeLeafController {

    public static final Gson gson = new Gson();

    @GetMapping("/") //For making this work, we should annotate the controller with @Controller and not @RestController
    public String getHomePage(Model model, @RequestParam(required = false, defaultValue = "Dishant") String name) {
        model.addAttribute("name", name);
        model.addAttribute("title", "Thymeleaf title");
        return "home";
    }

    @GetMapping("/status")
    public String status() {
        return "OK!";
    }

    @GetMapping(value = "/check", produces = "application/json")
    public CompletionStage<String> checkForObj() {
        log.info("Request for check method");
        ExecutorService es = Executors.newFixedThreadPool(2, new CustomizableThreadFactory("my-sad-thread-"));

        CompletableFuture<String> string = supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Thread name: {}", Thread.currentThread().getName());
            return "Dishant";
        }, es).thenApply(name -> {
            log.info("Thread name in thenApply: {}", Thread.currentThread().getName());
            return "Hello " + name;
        });
        log.info("Returned from check for obj");
        return string;
    }
}
