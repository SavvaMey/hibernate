package lazyInitializationexception;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "modelCar")
public class ModelCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "markId")
    private Mark mark;

    public static ModelCar of(String name, Mark mark) {
        ModelCar model = new ModelCar();
        model.name = name;
        model.mark = mark;
        return model;
    }

    public ModelCar() {
    }

    public ModelCar(int id) {
        this.id = id;
    }

    public ModelCar(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModelCar modelCars = (ModelCar) o;
        return id == modelCars.id
                && Objects.equals(name, modelCars.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ModelCar{"
                + "id=" + id
                + ", name='" + name
                + '\''
                + ", mark="
                + mark
                + '}';
    }

}
