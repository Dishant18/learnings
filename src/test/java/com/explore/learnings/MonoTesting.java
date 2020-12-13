package com.explore.learnings;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTesting {

    @Test
    public void testMono() {
        Mono<String> mono = Mono.just("Mono Test");

        StepVerifier.create(mono.log())
                .expectNext("Mono Test")
                .verifyComplete();
    }

    @Test
    public void testMono_WithError() {
        Mono<String> mono = Mono.error(new RuntimeException("Error!"));

        StepVerifier.create(mono.log())
                .expectErrorMessage("Error!")
                .verify();
    }
}
