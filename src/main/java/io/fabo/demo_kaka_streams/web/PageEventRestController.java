package io.fabo.demo_kaka_streams.web;

import io.fabo.demo_kaka_streams.entity.PageEvent;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class PageEventRestController {

    private final StreamBridge streamBridge;

    @GetMapping("/publish/{topic}/{name}")
    public PageEvent publish(@PathVariable String topic, @PathVariable String name) {

        PageEvent event = PageEvent.builder()
                .name(name)
                .user("fahd")
                .date(LocalDateTime.now())
                .duration(new Random().nextLong(9000))
                .build();

        final boolean isSent = streamBridge.send(
                topic,
                event
        );

        return isSent? event : PageEvent.builder().build();
    }
}
