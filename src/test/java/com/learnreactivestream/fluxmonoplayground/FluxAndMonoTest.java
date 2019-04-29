package com.learnreactivestream.fluxmonoplayground;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void fluxTest(){
        Flux<String> stringFlux = Flux.just("Spring","Java")
                /*.concatWith(Flux.error(new RuntimeException("Exception in Runtime")))*/
                .concatWith(Flux.just("After Error"))
                .log();

        stringFlux
                .subscribe(System.out::println,
                        (e)-> System.err.println(e),
                        ()-> System.out.println("Completed"));

    }
    @Test
    public void fluxTestWithoutError(){
        Flux<String> stringFlux = Flux.just("Spring","Java").log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring","Java")
                .verifyComplete();

    }

    @Test
    public void fluxTestWithError() {
        Flux<String> stringFlux = Flux.just("Spring", "Java")
                .concatWith(Flux.error(new RuntimeException("Error occured")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Java")
                .expectError(RuntimeException.class)
                .verify();
    }
}
