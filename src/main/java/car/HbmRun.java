package car;

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

            Integer idOne = (Integer) session.save(ModelCar.of("x1"));
            Integer idTwo = (Integer) session.save(ModelCar.of("x2"));
            Integer idThree = (Integer) session.save(ModelCar.of("x3"));
            Integer idFour = (Integer) session.save(ModelCar.of("x4"));
            Integer idFive = (Integer) session.save(ModelCar.of("x5"));

            Car car = Car.of("bmw");

            car.addModel(session.load(ModelCar.class, idOne));
            car.addModel(session.load(ModelCar.class, idTwo));
            car.addModel(session.load(ModelCar.class, idThree));
            car.addModel(session.load(ModelCar.class, idFour));
            car.addModel(session.load(ModelCar.class, idFive));

            session.save(car);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
