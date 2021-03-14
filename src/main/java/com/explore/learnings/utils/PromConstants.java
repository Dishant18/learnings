package com.explore.learnings.utils;

import io.prometheus.client.Counter;
import io.prometheus.client.Summary;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.nanoTime;

public class PromConstants {

    public static Map<String, Counter> counters = new HashMap<>();
    public static Map<String, Summary> summaries = new HashMap<>();

    public static void incrementCount(String name) {
        if (!counters.containsKey(name)) {
            Counter counter = Counter.build(name, name + "-help").register();
            counters.put(name, counter);
        }
        counters.get(name).inc();
    }

    public static void reportLatency(String name, Long startTime) {
        if (!summaries.containsKey(name)) {
            Summary summary = Summary.build(name, name + "-help").quantile(0.95, 0.1).quantile(0.99, 0.1).register();
            summaries.put(name, summary);
        }
        Summary summary = summaries.get(name);
        summary.observe(nanoTime() - startTime);
    }
}
