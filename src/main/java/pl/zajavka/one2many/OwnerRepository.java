package pl.zajavka.one2many;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.hibernate.Session;
import pl.zajavka.HibernateUtil;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class OwnerRepository {
    Owner insertData(final Owner owner, final Set<Pet> pets) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            owner.setPets(pets);
            pets.forEach(pet -> pet.setOwner(owner));
            session.persist(owner);
            session.getTransaction().commit();
            return owner;
        }
    }

    List<Owner> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "FROM Owner";
            List<Owner> owners = session.createQuery(query, Owner.class).list();
            session.getTransaction().commit();
            return owners;
        }
    }

    Optional<Owner> getOwner(Integer ownerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            return Optional.ofNullable(session.find(Owner.class, ownerId));
        }
    }

    void updateOwner(Integer ownerId, Pet newPet) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            Owner owner = session.find(Owner.class, ownerId);
            owner.getPets().add(newPet);
            session.getTransaction().commit();
        }
    }

    void deleteOwner(Integer ownerId) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            session.remove(session.find(Owner.class, ownerId));
            session.getTransaction().commit();
        }
    }

    void deleteAll() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "select owner from Owner owner";
            session.createQuery(query, Owner.class).list().forEach(session::remove);
            session.getTransaction().commit();
        }
    }

    int insertHQL() {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        int result;
        try {
            entityManager = HibernateUtil.getSession();
            if (Objects.isNull(entityManager)) {
                throw new RuntimeException("EntityManager is null");
            }
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("""
                    INSERT Owner (name, surname, phone, email) 
                    VALUES ('Romek', 'Zabawniacha', '+48 658 745 322', 'romek@zajavka.pl')
                    """);
            result = query.executeUpdate();
            transaction.commit();
            entityManager.close();
        } catch (RuntimeException exception) {
            if (Objects.nonNull(transaction) && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return result;
    }

    int updateHQL(final String oldEmail, final String newPhone, final String newEmail) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        int result;
        try {
            entityManager = HibernateUtil.getSession();
            if (Objects.isNull(entityManager)) {
                throw new RuntimeException("EntityManager is null");
            }
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("""
                    UPDATE Owner ow 
                    SET ow.phone = :newPhone, ow.email = :newEmail
                    WHERE ow.email = :oldEmail
                    """);
            query
                    .setParameter("oldEmail", oldEmail)
                    .setParameter("newEmail", newEmail)
                    .setParameter("newPhone", newPhone);
            result = query.executeUpdate();
            transaction.commit();
            entityManager.close();
        } catch (RuntimeException exception) {
            if (Objects.nonNull(transaction) && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return result;
    }

    int deleteHQL(final String email) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        int result;
        try {
            entityManager = HibernateUtil.getSession();
            if (Objects.isNull(entityManager)) {
                throw new RuntimeException("EntityManager is null");
            }
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("""
                    DELETE FROM Owner ow 
                    WHERE ow.email = :email
                    """);
            query
                    .setParameter("email", email);

            result = query.executeUpdate();
            transaction.commit();
            entityManager.close();
        } catch (RuntimeException exception) {
            if (Objects.nonNull(transaction) && transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            if (Objects.nonNull(entityManager)) {
                entityManager.close();
            }
        }
        return result;
    }

    List<Owner> selectExample1() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "FROM Owner";
            List<Owner> owners = session.createQuery(query, Owner.class).list();
            session.getTransaction().commit();
            return owners;
        }
    }


    List<Object[]> selectExample2() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT ow.id, ow.name FROM Owner ow";
            List<Object[]> owners = session.createQuery(query, Object[].class).getResultList();
            session.getTransaction().commit();
            return owners;
        }
    }

    List<OwnerTemp> selectExample2a() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT new pl.zajavka.one2many.OwnerTemp(ow.id, ow.name) FROM Owner ow";
            ;
            List<OwnerTemp> owners = session.createQuery(query, OwnerTemp.class).getResultList();
            session.getTransaction().commit();
            return owners;
        }
    }

    List<Owner> selectExample3() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT ow FROM Owner ow WHERE ow.email = :email";
            List<Owner> owners = session
                    .createQuery(query, Owner.class)
                    .setParameter("email", "stefan@zajavka.pl")
                    .getResultList();
            session.getTransaction().commit();
            return owners;
        }
    }

    List<Owner> selectExample4() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT ow FROM Owner ow ORDER BY ow.email ASC, ow.name DESC";
            List<Owner> owners = session
                    .createQuery(query, Owner.class)
                    .getResultList();
            session.getTransaction().commit();
            return owners;
        }
    }

    List<Owner> selectExample5() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT ow FROM Owner ow ORDER BY ow.email ASC, ow.name DESC";
            List<Owner> owners = session
                    .createQuery(query, Owner.class)
                    .setFirstResult(1)
                    .setMaxResults(3)
                    .getResultList();
            session.getTransaction().commit();
            return owners;
        }
    }

    Optional<Owner> selectExample6(String name) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String query = "SELECT ow FROM Owner ow WHERE ow.name = :name";
            Optional<Owner> owners = session
                    .createQuery(query, Owner.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return owners;
        }
    }

    void selectExample7() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String select7_1 = "SELECT ow FROM Owner ow INNER JOIN ow.pets pt";
            session.createQuery(select7_1, Owner.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            session.getTransaction().commit();

        }
    }

    void saveTestData() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            // Toy creation and saving
            Toy toy1 = ExampleData.someToy1();
            Toy toy2 = ExampleData.someToy2();
            Toy toy3 = ExampleData.someToy3();
            Toy toy4 = ExampleData.someToy4();
            session.persist(toy1);
            session.persist(toy2);
            session.persist(toy3);
            session.persist(toy4);
            // Tet creation
            Pet pet1 = ExampleData.somePet1();
            Pet pet2 = ExampleData.somePet2();
            Pet pet3 = ExampleData.somePet3();
            Pet pet4 = ExampleData.somePet4();
            pet1.setToys(Set.of(toy1, toy2));
            pet2.setToys(Set.of(toy2, toy3));
            pet3.setToys(Set.of(toy1, toy2, toy3));
            pet4.setToys(Set.of(toy2, toy3, toy4));
            // Owner creation and saving
            Owner owner1 = ExampleData.someOwner1();
            Owner owner2 = ExampleData.someOwner2();
            owner1.setPets(Set.of(pet1, pet2));
            owner2.setPets(Set.of(pet3, pet4));
            owner1.getPets().forEach(pet -> pet.setOwner(owner1));
            owner2.getPets().forEach(pet -> pet.setOwner(owner2));
            session.persist(owner1);
            session.persist(owner2);
            session.getTransaction().commit();
        }
    }

    void selectExample8() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String select8 = """
                    SELECT ow FROM Owner ow
                    INNER JOIN FETCH ow.pets pt
                    INNER JOIN FETCH pt.toys ts
                    """;
            session.createQuery(select8, Owner.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            session.getTransaction().commit();
        }
    }

    void selectExample9() {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();
            String select9_2 = """
                    SELECT new pl.zajavka.one2many.ToyStat(
                    MAX(t.what),
                    SUM(t.toyId) / COUNT(t.toyId)
                    ) FROM Toy t
                    """;
            session.createQuery(select9_2, ToyStat.class)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            session.getTransaction().commit();
        }
    }
    void selectExampleNamedQuery(final String email) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            session.createNamedQuery("Owner.findOwnerByEmail", Owner.class)
                    .setParameter("email",email)
                    .getResultList()
                    .forEach(entity -> System.out.println("###Entity: " + entity));

            session.getTransaction().commit();
        }
    }

}
