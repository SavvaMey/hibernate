package modelsAndRealations;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    public static Engine of(String name) {
        Engine engine = new Engine();
        engine.name = name;
        return engine;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id && Objects.equals(car, engine.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car);
    }

    @Override
    public String toString() {
        return "Engine{"
                + "id=" + id
                + ", car=" + car
                + '}';
    }
}
