package lazyInitializationexception;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


public class HbmCar {
    public static void main(String[] args) {
        List<Mark> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
//            Mark markCar = Mark.of("BMW");
//            session.save(markCar);
//
//            ModelCar model1 = ModelCar.of("x1", markCar);
//            ModelCar model2 = ModelCar.of("x2", markCar);
//            ModelCar model3 = ModelCar.of("x3", markCar);
//            session.save(model1);
//            session.save(model2);
//            session.save(model3);

            list = session.createQuery(
                    "select distinct c from Mark c join fetch c.modelCarsList"
            ).list();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Mark markCar : list) {
            for (ModelCar modelCar : markCar.getModelCarsList()) {
                System.out.println(modelCar);
            }
        }
    }
}
