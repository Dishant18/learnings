package com.explore.learnings.service;

//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.MeterRegistry;
//import io.micrometer.core.instrument.Timer;
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

import java.util.Random;

import static com.explore.learnings.utils.PromConstants.reportLatency;
import static java.lang.System.nanoTime;
import static java.lang.Thread.sleep;
import static reactor.core.publisher.Mono.just;

@Slf4j
@Service
public class PrometheusService {

//    @Autowired
//    MeterRegistry meterRegistry;
//
//
//    public Mono<String> test() {
//        int sleepDur = RandomUtils.nextInt() % 2000 + 100;
//        log.info("sleep dur: " + sleepDur);
//        requests.increment();
//        Timer.Sample sample = Timer.start();
//        try {
//            sleep(sleepDur);
//            return Mono.just("test");
//        } catch (Exception e) {
//            return Mono.just("fail");
//        } finally {
//            sample.stop(requestLatency);
//        }
//    }

    public Mono<String> test() {
        long start = nanoTime();
        int sleepDur = RandomUtils.nextInt() % 2000 + 100;
        log.info("sleep dur: " + sleepDur);
        try {
            sleep(sleepDur);
            return just("test");
        } catch (Exception e) {
            return just("fail");
        } finally {
            reportLatency("test_service", start);
        }
    }

    public Mono<String> something() {
        long start = nanoTime();
        try {
            return just("something");
        } finally {
            reportLatency("something_service", start);
        }
    }

}
