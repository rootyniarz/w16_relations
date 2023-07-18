package pl.zajavka.locks;

import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import org.hibernate.LockMode;
import org.hibernate.Session;
import pl.zajavka.HibernateUtil;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class EventRepository {
    void deleteAll() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<TicketEntity> deleteTicket = criteriaBuilder.createCriteriaDelete(
                    TicketEntity.class);
            deleteTicket.from(TicketEntity.class);
            session.createMutationQuery(deleteTicket).executeUpdate();
            CriteriaDelete<EventEntity> deleteEvent = criteriaBuilder.createCriteriaDelete(EventEntity
                    .class);
            deleteEvent.from(EventEntity.class);
            session.createMutationQuery(deleteEvent).executeUpdate();
            session.getTransaction().commit();
        }
    }

    EventEntity insertEmptyEvent(final EventEntity event) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(event);
            session.getTransaction().commit();
            return event;
        }
    }

    void saveNewTicket(String firstName, String lastName, Long eventId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            EventEntity event = session.find(EventEntity.class, eventId);
            Map<String, Object> properties = new HashMap<>();
            properties.put("jakarta.persistence.lock.timeout", 1000L);
            session.lock(event, LockModeType.PESSIMISTIC_WRITE, properties);
            if (event.getCapacity() <= event.getTickets().size()) {
                throw new RuntimeException("Capacity exceeded");
            }
            TicketEntity ticket = TicketEntity.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();
            event.addTicket(ticket);
            session.persist(ticket);
            session.getTransaction().commit();
        }
    }
    void changeDateTime(OffsetDateTime newDateTime, Long eventId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            EventEntity event = session.find(EventEntity.class, eventId);
            event.setDateTime(newDateTime);
            session.getTransaction().commit();
        }
    }

    }
