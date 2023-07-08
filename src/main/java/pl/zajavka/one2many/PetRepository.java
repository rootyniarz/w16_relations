package pl.zajavka.one2many;

import org.hibernate.Session;
import pl.zajavka.HibernateUtil;

import java.util.Objects;
import java.util.Set;

public class PetRepository {
    Pet insertData(final Pet pet) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            System.out.println("### BEFORE INSERT ###");
            session.beginTransaction();
            session.persist(pet);
            session.getTransaction().commit();
            System.out.println("### AFTER INSERT ###");
            return pet;
        }
    }
}
