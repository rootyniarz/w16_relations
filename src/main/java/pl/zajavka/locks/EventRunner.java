package pl.zajavka.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventRunner {
    public static void main(String[] args) {
        EventRepository eventRepository = new EventRepository();
        eventRepository.deleteAll();

        EventEntity event2 = eventRepository.insertEmptyEvent(EventEntityData.someEventEntity2());

        ExecutorService executor = Executors.newFixedThreadPool(50);
        executor.execute(() -> run(eventRepository, 1, event2));
        executor.execute(() -> run(eventRepository, 2, event2));
        executor.execute(() -> run(eventRepository, 3, event2));
        executor.execute(() -> run(eventRepository, 4, event2));
        executor.execute(() -> run(eventRepository, 5, event2));
        executor.execute(() -> run(eventRepository, 6, event2));
        executor.execute(() -> run(eventRepository, 7, event2));
        executor.execute(() -> run(eventRepository, 8, event2));
        executor.execute(() -> run(eventRepository, 9, event2));
        executor.execute(() -> run(eventRepository, 10, event2));
        executor.execute(() -> run(eventRepository, 11, event2));
        executor.execute(() -> run(eventRepository, 12, event2));
        executor.execute(() -> run(eventRepository, 13, event2));
        executor.execute(() -> run(eventRepository, 14, event2));
        executor.execute(() -> run(eventRepository, 15, event2));
        executor.execute(() -> run(eventRepository, 16, event2));
        executor.execute(() -> run(eventRepository, 17, event2));
        executor.execute(() -> run(eventRepository, 18, event2));
        executor.execute(() -> run(eventRepository, 19, event2));
        executor.execute(() -> run(eventRepository, 20, event2));
        executor.execute(() -> run(eventRepository, 21, event2));
        executor.execute(() -> run(eventRepository, 22, event2));
        executor.execute(() -> run(eventRepository, 23, event2));
        executor.execute(() -> run(eventRepository, 24, event2));
        executor.execute(() -> run(eventRepository, 25, event2));
        executor.execute(() -> run(eventRepository, 26, event2));
        executor.execute(() -> run(eventRepository, 27, event2));
        executor.execute(() -> run(eventRepository, 28, event2));
        executor.execute(() -> run(eventRepository, 29, event2));
        executor.execute(() -> run(eventRepository, 30, event2));
        executor.execute(() -> run(eventRepository, 31, event2));
        executor.execute(() -> run(eventRepository, 32, event2));
        executor.execute(() -> run(eventRepository, 33, event2));
        executor.execute(() -> run(eventRepository, 34, event2));
        executor.execute(() -> run(eventRepository, 35, event2));
        executor.execute(() -> run(eventRepository, 36, event2));
        executor.execute(() -> run(eventRepository, 37, event2));
        executor.execute(() -> run(eventRepository, 38, event2));
        executor.execute(() -> run(eventRepository, 39, event2));
        executor.execute(() -> run(eventRepository, 40, event2));
        executor.execute(() -> run(eventRepository, 41, event2));
        executor.execute(() -> run(eventRepository, 42, event2));
        executor.execute(() -> run(eventRepository, 43, event2));
        executor.execute(() -> run(eventRepository, 44, event2));
        executor.execute(() -> run(eventRepository, 45, event2));
        executor.execute(() -> run(eventRepository, 46, event2));
        executor.execute(() -> run(eventRepository, 47, event2));
        executor.execute(() -> run(eventRepository, 48, event2));
        executor.execute(() -> run(eventRepository, 49, event2));
        executor.execute(() -> run(eventRepository, 50, event2));


        executor.shutdown();
    }

    private static void run(EventRepository eventRepository, int counter, EventEntity event2) {
        try {
            Thread.sleep(1000);
            eventRepository.saveNewTicket("name" + counter, "surname" + counter, event2.getEventId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
