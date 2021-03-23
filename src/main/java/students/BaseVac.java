package students;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "base_vac")
public class BaseVac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vac> vacs = new ArrayList<>();

    public static BaseVac of(String name) {
        BaseVac baseVac = new BaseVac();
        baseVac.setName(name);
        return baseVac;
    }

    public void addVac(Vac vac) {
        this.vacs.add(vac);
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

    public List<Vac> getVacs() {
        return vacs;
    }

    public void setVacs(List<Vac> vacs) {
        this.vacs = vacs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseVac baseVac = (BaseVac) o;
        return id == baseVac.id && Objects.equals(name, baseVac.name) && Objects.equals(vacs, baseVac.vacs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vacs);
    }

    @Override
    public String toString() {
        return "BaseVac{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vacs=" + vacs +
                '}';
    }
}
