package books;

import manytomany.Address;
import manytomany.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book one = Book.of("LoTR");
            Book two = Book.of("GP");

            Author first = Author.of("Tolkien");
            first.getBooks().add(one);
            first.getBooks().add(two);

            Author second = Author.of("Rowling");
            second.getBooks().add(two);

            session.persist(first);
            session.persist(second);

            Author another = session.get(Author.class, 1);
            session.remove(another);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
