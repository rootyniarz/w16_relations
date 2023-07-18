package pl.zajavka.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventRunner {
    public static void main(String[] args) {
        EventRepository eventRepository = new EventRepository();
        eventRepository.deleteAll();

        EventEntity event1 = eventRepository.insertEmptyEvent(EventEntityData.someEventEntity1());
        EventEntity event2 = eventRepository.insertEmptyEvent(EventEntityData.someEventEntity2());

        eventRepository.saveNewTicket("Karol", "Zajavka", event1.getEventId());
        eventRepository.saveNewTicket("Anna", "Zajavka", event2.getEventId());

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            eventRepository.saveNewTicket("Stefan", "Adamski", event2.getEventId());
            try {
                Thread.sleep(1_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        executor.execute(() -> {
            eventRepository.saveNewTicket("Agnieszka", "Programistka", event2.getEventId());
            try {
                Thread.sleep(1_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        executor.shutdown();
    }
}
