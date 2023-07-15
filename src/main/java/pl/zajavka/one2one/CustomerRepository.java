package pl.zajavka.one2one;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.zajavka.HibernateUtil;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerRepository {
    Customer insertCustomer(final Customer customer) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    List<Customer> listCustomers() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT cust FROM Customer cust";
            List<Customer> customers = session.createQuery(query, Customer.class).list();
            session.getTransaction().commit();
            return customers;
        }
    }

    Optional<Customer> getCustomer(Integer customerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            return Optional.ofNullable(session.find(Customer.class, customerId));
        }
    }

    void updateCustomer(Integer customerId, Address newAddress) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            Customer customer = session.find(Customer.class, customerId);
            customer.setAddress(newAddress);
            session.getTransaction().commit();
        }
    }

    void deleteCustomer(Integer customerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.remove(session.find(Customer.class, customerId));
            session.getTransaction().commit();
        }
    }

    public void deleteAll() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "select emp from Customer emp";
            session.createQuery(query, Customer.class).list().forEach(object -> session.remove(object));
            session.getTransaction().commit();
        }
    }

    public void criteriaExample() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            System.out.println("BEFORE CRITERIA");

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);

            Query<Customer> query = session.createQuery(criteriaQuery);
            List<Customer> resultList = query.getResultList();
            resultList.forEach(entity -> System.out.println("### ENTITY: " + entity));


            System.out.println("AFTER CRITERIA");
            session.getTransaction().commit();
        }
    }

    public void criteriaExample2() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            System.out.println("BEFORE CRITERIA");
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery
                    .select(root)
                    .where(
                            criteriaBuilder.and(
                                    criteriaBuilder.like(root.get("email"), "%r%"),
                                    criteriaBuilder.isNotNull(root.get("phone"))
                            ))
                    .orderBy(criteriaBuilder.desc(root.get("email")));
            TypedQuery<Customer> query = session.createQuery(criteriaQuery);
            List<Customer> result = query.getResultList();
            result.forEach(customer -> System.out.println("###Customer: " + customer));

            System.out.println("AFTER CRITERIA");
            session.getTransaction().commit();
        }
    }

    public void criteriaExample3() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            System.out.println("BEFORE CRITERIA");
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            root.join("address", JoinType.INNER);

            Query<Customer> query = session.createQuery(criteriaQuery);
            List<Customer> result = query.getResultList();
            result.forEach(customer -> System.out.println("###ENTITY: " + customer));

            System.out.println("AFTER CRITERIA");
            session.getTransaction().commit();
        }
    }

    public void criteriaUpdateExample3() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            System.out.println("BEFORE CRITERIA");
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<Customer> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Customer.class);
            Root<Customer> root = criteriaUpdate.from(Customer.class);
            criteriaUpdate.set("name", "Tomasz");
            criteriaUpdate.where(criteriaBuilder.equal(root.get("email"), "adrian@zajavka.pl"));
            int i = session.createMutationQuery(criteriaUpdate).executeUpdate();


            System.out.println("\nAFTER CRITERIA");
            System.out.println("Result: " + i);
            session.getTransaction().commit();
        }
    }

    public void criteriaDeleteExample3() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            System.out.println("BEFORE CRITERIA");
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Customer> criteriaDelete = criteriaBuilder.createCriteriaDelete(Customer.class);
            Root<Customer> root = criteriaDelete.from(Customer.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("email"), "adrian@zajavka.pl"));

            int i = session.createMutationQuery(criteriaDelete).executeUpdate();

            System.out.println("\nAFTER CRITERIA");
            System.out.println("Result: " + i);
            session.getTransaction().commit();
        }
    }


}
