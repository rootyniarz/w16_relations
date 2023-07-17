package pl.zajavka.cache;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ExampleData {

    static CachedEmployee someEmployee1() {
        return CachedEmployee.builder()
                .name("Agnieszka")
                .surname("Pracownik")
                .salary(new BigDecimal("5910.73"))
                .since(OffsetDateTime.of(2020,1,1,10,10,10,0, ZoneOffset.UTC))
                .build();
    }
    static CachedEmployee someEmployee2() {
        return CachedEmployee.builder()
                .name("Stefan")
                .surname("Nowacki")
                .salary(new BigDecimal("3455.12"))
                .since(OffsetDateTime.of(2021,1,1,10,10,10,0,ZoneOffset.UTC))
                .build();
    }
    static CachedEmployee someEmployee3() {
        return CachedEmployee.builder()
                .name("Tomasz")
                .surname("Adamski")
                .salary(new BigDecimal("6112.42"))
                .since(OffsetDateTime.of(2022,1,1,10,10,10,0,ZoneOffset.UTC))
                .build();
    }
}
