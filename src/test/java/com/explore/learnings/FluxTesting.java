package com.explore.learnings;

import com.explore.learnings.model.UserFileData;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Source: https://www.youtube.com/watch?v=iuNaIy1c9CQ&list=PLnXn1AViWyL70R5GuXt_nIDZytYBnvBdd&index=10&ab_channel=DilipS
 */
public class FluxTesting {

    @Test
    public void testFluxMethods() {
        Flux<String> stringFlux = Flux.just("Flux", "Mono", "Reactive")
                .concatWith(Flux.error(new RuntimeException("Error!")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Flux")
                .expectNext("Mono")
                .expectNext("Reactive")
                .expectErrorMessage("Error!")
                .verify();
    }

    @Test
    public void testFluxElementsCount() {
        Flux<String> stringFlux = Flux.just("Flux", "Mono", "Reactive")
                .concatWith(Flux.error(new RuntimeException("Error!")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("Error!")
                .verify();
    }
}
