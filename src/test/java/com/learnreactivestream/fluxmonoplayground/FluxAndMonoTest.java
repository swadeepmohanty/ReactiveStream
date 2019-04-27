package com.learnreactivestream.fluxmonoplayground;

import org.junit.Test;
import reactor.core.publisher.Flux;

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
}
