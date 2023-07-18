package pl.zajavka;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.stat.Statistics;
import pl.zajavka.cache.CachedEmployee;
import pl.zajavka.locks.EventEntity;
import pl.zajavka.locks.TicketEntity;
import pl.zajavka.many2many.Employee;
import pl.zajavka.many2many.Project;
import pl.zajavka.one2many.Owner;
import pl.zajavka.one2many.Pet;
import pl.zajavka.one2many.Toy;
import pl.zajavka.one2one.Address;
import pl.zajavka.one2one.Customer;

import java.util.Map;

public class HibernateUtil {
    private static final Map<String, Object> SETTINGS = Map.ofEntries(
            Map.entry(Environment.DRIVER, "org.postgresql.Driver"),
            Map.entry(Environment.URL, "jdbc:postgresql://localhost:5432/osoba"),
            Map.entry(Environment.USER, "postgres"),
            Map.entry(Environment.PASS, "postgres"),
            Map.entry(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect"),
            Map.entry(Environment.CONNECTION_PROVIDER, "org.hibernate.hikaricp.internal.HikariCPConnectionProvider"),
//            Map.entry(Environment.GENERATE_STATISTICS, true),
            Map.entry(Environment.HBM2DDL_AUTO, "none"),
            Map.entry(Environment.SHOW_SQL, true),
            Map.entry(Environment.FORMAT_SQL, false),
            Map.entry(Environment.USE_SQL_COMMENTS, false)
    );
    private static final Map<String, Object> HIKARI_CP_SETTINGS = Map.ofEntries(
            Map.entry("hibernate.hikari.connectionTimeout", "20000"),
            Map.entry("hibernate.hikari.minimumIdle", "10"),
            Map.entry("hibernate.hikari.maximumPoolSize", "20"),
            Map.entry("hibernate.hikari.idleTimeout", "300000")
    );

    private static final Map<String, Object> CACHE_SETTINGS = Map.ofEntries(
            Map.entry(Environment.CACHE_REGION_FACTORY, "jcache"),
            Map.entry("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider"),
            Map.entry("hibernate.javax.cache.uri", "/META-INF/ehcache.xml"),
            Map.entry(Environment.USE_SECOND_LEVEL_CACHE, true)
    );


    private static final SessionFactory sessionFactory = loadSessionFactory();

    private static SessionFactory loadSessionFactory() {
        try {
            ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(SETTINGS)
                    .applySettings(HIKARI_CP_SETTINGS)
                    .applySettings(CACHE_SETTINGS)
                    .build();
            Metadata metadata = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Pet.class)
                    .addAnnotatedClass(Toy.class)
                    .addAnnotatedClass(Owner.class)
                    .addAnnotatedClass(CachedEmployee.class)
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Project.class)
                    .addAnnotatedClass(EventEntity.class)
                    .addAnnotatedClass(TicketEntity.class)
                    .getMetadataBuilder()
                    .build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void closeSessionFactory() {
        try {
            sessionFactory.close();
        } catch (Throwable ex) {
            System.err.println("Exception while closing SessionFactory: " + ex);
        }
    }

    public static Session getSession() {
        try {
            return sessionFactory.openSession();
        } catch (Throwable ex) {
            System.err.println("Exception while getting Session: " + ex);
        }
        return null;
    }

    public static Statistics getStatistics() {
        return sessionFactory.getStatistics();
    }

}
