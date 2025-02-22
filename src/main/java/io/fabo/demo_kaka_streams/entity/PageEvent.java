package io.fabo.demo_kaka_streams.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor  @NoArgsConstructor @Getter @Setter @Builder @ToString
public class PageEvent {

    private String name;
    private String user;
    private LocalDateTime date;
    private long duration;
}
