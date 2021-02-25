package com.explore.learnings.controller;

import com.explore.learnings.service.PrometheusService;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

@RestController("/prom")
@Slf4j
public class PrometheusTest {

    @Autowired
    private PrometheusService prometheusService;

    @GetMapping("/test")
    public Mono<String> testOne() {
        return prometheusService.test();
    }

//    @RequestMapping(path = "/prometheus-metrics")
//    public void metrics(Writer responseWriter) throws IOException {
//        TextFormat.write004(responseWriter, CollectorRegistry.defaultRegistry.metricFamilySamples());
//        responseWriter.close();
//    }
}
