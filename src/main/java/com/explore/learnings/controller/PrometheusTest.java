package com.explore.learnings.controller;

import com.explore.learnings.service.PrometheusService;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.explore.learnings.utils.PromConstants.incrementCount;

@RestController
@Slf4j
public class PrometheusTest {

    @Autowired
    private PrometheusService prometheusService;

    @GetMapping("/test")
    public Mono<String> testOne() {
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        LocalTime localTime = LocalTime.now(zoneId);
        log.info("locatime : {} {} {}", localTime.getHour(), localTime.getMinute(), localTime.getSecond());
        incrementCount("test");
        LocalTime nextTime = localTime.plusHours(2);
        System.out.println(localTime.plusHours(2).compareTo(localTime) + " " + localTime.compareTo(localTime) + " " + localTime.minusHours(2).compareTo(localTime));
        return prometheusService.test();
    }

    @GetMapping("/something")
    public Mono<String> something() {
        incrementCount("something");
        return prometheusService.something();
    }

    @GetMapping("/collect")
    public ResponseEntity collect() throws IOException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {
            TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
        }
        return new ResponseEntity<>(stream.toByteArray(), HttpStatus.OK);
    }
}
