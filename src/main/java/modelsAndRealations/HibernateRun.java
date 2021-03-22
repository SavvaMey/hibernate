package modelsAndRealations;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateRun {
    public static void main(String[] args) {
         final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
         final SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        SessionFactory factory = sf.getSessionFactory();
    }
}
