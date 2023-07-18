package pl.zajavka.locks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class EventEntityData {
    static EventEntity someEventEntity1() {
        return EventEntity.builder()
                .eventName("Standup")
                .capacity(5)
                .dateTime(OffsetDateTime.of(LocalDate.of(2024, 10, 2), LocalTime.of(18, 0, 0), ZoneOffset
                        .ofHours(2)))
                .build();
    }

    static EventEntity someEventEntity2() {
        return EventEntity.builder()
                .eventName("Koncert")
                .capacity(2)
                .dateTime(OffsetDateTime.of(LocalDate.of(2022, 9, 14), LocalTime.of(21, 0, 0), ZoneOffset
                        .ofHours(2)))
                .build();
    }

}
