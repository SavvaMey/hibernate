package students;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate one = Candidate.of("Alex", 3, 350.3);
            Candidate two = Candidate.of("Nikolay", 5, 100);
            Candidate three = Candidate.of("Nikita", 8, 221.34);

            session.save(one);
            session.save(two);
            session.save(three);

            // select все кандидаты
            Query query = session.createQuery("from Candidate");
            for (Object st : query.list()) {
                System.out.println(st);
            }
            // select по id
            Query queryT = session.createQuery("from Candidate s where s.id = 1");
            System.out.println(queryT.uniqueResult());

            // select по name
            Query queryName = session.createQuery("from Candidate s where s.name = :fName");
            queryName.setParameter("fName", "Nikita");
            System.out.println(queryName.uniqueResult());

            // update
             session.createQuery(
                    "update Candidate s set s.experience = :newExp, s.name = :newName where s.id = :fId")
                     .setParameter("newExp", 15)
                    .setParameter("newName", "Gaz")
                    .setParameter("fId", 2)
                    .executeUpdate();

             Query queryUp = session.createQuery("from Candidate s where s.id = 2");
            System.out.println(queryUp.uniqueResult());

            // delete
            session.createQuery("delete from Candidate where id = :fId")
                    .setParameter("fId", 1)
                    .executeUpdate();

            Query querySelect = session.createQuery("from Candidate");
            for (Object st : querySelect.list()) {
                System.out.println(st);
            }

//            session.createQuery("insert into Student (name, age, city) "
//                    + "select concat(s.name, 'NEW'), s.age + 5, s.city  "
//                    + "from Student s where s.id = :fId")
//                    .setParameter("fId", 1)
//                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
