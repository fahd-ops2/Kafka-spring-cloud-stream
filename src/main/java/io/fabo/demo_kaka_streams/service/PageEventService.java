package io.fabo.demo_kaka_streams.service;

import io.fabo.demo_kaka_streams.entity.PageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class PageEventService {

    @Bean
    public Consumer<PageEvent> pageEventConsumer() {
        return (input) -> {
            System.out.println("--------------------------------");
            System.out.println("Received Page Event: " + input.toString());
            System.out.println("--------------------------------");
        };
    }

    @Bean
    public Supplier<PageEvent> pageEventSupplier() {
        return () -> {
            PageEvent event = PageEvent.builder()
                    .name("New Page")
                    .user("John Doe")
                    .date(LocalDateTime.now())
                    .duration(10000L)
                    .build();

            System.out.println("--------------------------------");
            System.out.println("Generated Page Event: " + event.toString());
            System.out.println("--------------------------------");

            return event;
        };
    }

    @Bean
    public Function<PageEvent,PageEvent> pageEventFunction() {
        return (input) -> {
            input.setName("Processed Page");
            input.setUser("Jane Doe");
            System.out.println("--------------------------------");
            System.out.println("Processed Page Event: " + input);
            System.out.println("--------------------------------");
            return input;
        };
    }
}
