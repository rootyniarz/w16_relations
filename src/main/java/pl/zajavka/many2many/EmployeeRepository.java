package pl.zajavka.many2many;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import pl.zajavka.HibernateUtil;

import java.util.*;


public class EmployeeRepository {
    List<Employee> insertData(final List<Employee> employees) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            employees.forEach(session::persist);
            session.getTransaction().commit();
            return employees;
        }
    }
    List<Employee> listEmployees() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT employee FROM Employee employee";
            List<Employee> employees = session.createQuery(query, Employee.class).list();
            session.getTransaction().commit();
            return employees;
        }
    }
    Optional<Employee> getEmployee(Integer employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            return Optional.ofNullable(session.find(Employee.class, employeeId));
        }
    }
    void updateEmployee(Integer employeeId, Project newProject) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            Employee employee = session.find(Employee.class, employeeId);
            employee.getProjects().add(newProject);
            session.getTransaction().commit();
        }
    }
    void deleteEmployee(Integer employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
                    session.beginTransaction();
            session.remove(session.find(Employee.class, employeeId));
            session.getTransaction().commit();
        }
    }
    void deleteAll() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "select employee from Employee employee";
            session.createQuery(query, Employee.class).list().forEach(session::remove);
            session.getTransaction().commit();
        }
    }

    Optional<Employee> getEmployeeWithStats(Integer employeeId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            Employee employee = session.find(Employee.class, employeeId);
                    stats(HibernateUtil.getStatistics());
            System.out.printf("###Employee: %s %s%n", employee.getName(), employee.getSurname());
            return Optional.ofNullable(employee);
        }
    }
    public static void stats(Statistics statistics) {
        System.out.println("Misses in second level cache:" + statistics.getSecondLevelCacheMissCount());
        System.out.println("Added to second level cache:" + statistics.getSecondLevelCachePutCount());
        System.out.println("Found in second level cache:" + statistics.getSecondLevelCacheHitCount());
        statistics.clear();
    }


}
