package com.explore.learnings;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnablePrometheusEndpoint
@EntityScan("com.explore.learnings")
public class Driver {

    public static void main(String[] args) {
        SpringApplication.run(Driver.class, args);
    }
}
