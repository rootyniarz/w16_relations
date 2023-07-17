package pl.zajavka.cache;

import org.hibernate.Session;
import pl.zajavka.HibernateUtil;

import java.util.Objects;

public class CachedEmployeeRepository {

    CachedEmployee insertData(final CachedEmployee cachedEmployee) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(cachedEmployee);
            session.getTransaction().commit();
            return cachedEmployee;
        }
    }

    void deleteAll() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "select employee from Employee employee";
            session.createQuery(query, pl.zajavka.many2many.Employee.class).list().forEach(session::remove);
            session.getTransaction().commit();
        }
    }

    void l1c(int employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            CachedEmployee e1 = session.find(CachedEmployee.class, employeeId);
            System.out.printf("### E1 %s %s%n", e1.getName(), e1.getSurname());

            CachedEmployee e2 = session.find(CachedEmployee.class, employeeId);
            System.out.printf("### E2 %s %s%n", e2.getName(), e2.getSurname());

            CachedEmployee e3 = session.find(CachedEmployee.class, employeeId);
            System.out.printf("### E3 %s %s%n", e3.getName(), e3.getSurname());

            session.evict(e1);

            CachedEmployee e4 = session.find(CachedEmployee.class, employeeId);
            System.out.printf("### E4 %s %s%n", e4.getName(), e4.getSurname());

            CachedEmployee e5 = session.find(CachedEmployee.class, employeeId);
            System.out.printf("### E5 %s %s%n", e5.getName(), e5.getSurname());

            session.clear();

            CachedEmployee e6 = session.find(CachedEmployee.class, employeeId);
            System.out.printf("### E6 %s %s%n", e6.getName(), e6.getSurname());

            CachedEmployee e7 = session.find(CachedEmployee.class, employeeId);
            System.out.printf("### E7 %s %s%n", e7.getName(), e7.getSurname());

            session.getTransaction().commit();

        }

    }
}
