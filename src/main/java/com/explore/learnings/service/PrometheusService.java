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

import static java.lang.Thread.sleep;

@Slf4j
@Service
public class PrometheusService {

//    @Autowired
//    MeterRegistry meterRegistry;
//
    public static Counter requests;
    public static Histogram requestLatency;
//
    @PostConstruct
    public void init() {
        requests = Counter.build("tot_requests_my", "total requests").register();
        requestLatency = Histogram
                .build("tot_req_lat_my", "tot req lat my")
                .register();
    }
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
        requests.inc();
        Histogram.Timer timer = requestLatency.startTimer();
        int sleepDur = RandomUtils.nextInt() % 2000 + 100;
        log.info("sleep dur: " + sleepDur);
        try {
            sleep(sleepDur);
            return Mono.just("test");
        } catch (Exception e) {
            return Mono.just("fail");
        } finally {
            timer.observeDuration();
        }
    }

}
