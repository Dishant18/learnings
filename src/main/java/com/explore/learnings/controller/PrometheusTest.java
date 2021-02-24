package com.explore.learnings.controller;

import com.explore.learnings.service.PrometheusService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/prom")
public class PrometheusTest {

    @Autowired
    private PrometheusService prometheusService;

    @GetMapping("/test")
    @Timed(value = "test_one", percentiles = {0.5, 0.95})
    public Mono<String> testOne() {
        return prometheusService.test();
    }
}
