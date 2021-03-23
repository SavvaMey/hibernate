package students;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmNewRun {
    public static void main(String[] args) {
        Candidate rsl = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
                SessionFactory sf = new MetadataSources(registry)
                        .buildMetadata()
                        .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

    //        Candidate candidate = Candidate.of("Alex", 10, 350);
    //        BaseVac baseVac = BaseVac.of("HabrCar");
    //        candidate.setBaseVac(baseVac);
    //        baseVac.addVac(Vac.of("java"));
    //        baseVac.addVac(Vac.of("DBA"));
    //        session.save(candidate);

            rsl = session.createQuery(
                    "select distinct cn from Candidate cn "
                            + "join fetch cn.baseVac a "
                            + "join fetch a.vacs b "
                            + "where cn.id = :sId", Candidate.class
            )
                    .setParameter("sId", 1)
                    .uniqueResult();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(rsl);
    }
}
