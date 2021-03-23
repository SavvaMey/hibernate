package orders;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.persistence.criteria.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrdersStoreLogic {
    private final BasicDataSource pool;

    public OrdersStoreLogic(BasicDataSource pool) {
        this.pool = pool;
    }

    public OrderStore save(OrderStore order) {
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement(
                     "INSERT INTO orders(name, description, created) VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pr.setString(1, order.getName());
            pr.setString(2, order.getDescription());
            pr.setTimestamp(3, order.getCreated());
            pr.execute();
            ResultSet id = pr.getGeneratedKeys();
            if (id.next()) {
                order.setId(id.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public Collection<OrderStore> findAll() {
        List<OrderStore> rsl = new ArrayList<>();
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement(
                     "SELECT * FROM orders")) {
            try (ResultSet rs = pr.executeQuery()) {
                while (rs.next()) {
                    rsl.add(
                            new OrderStore(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getTimestamp(4)
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public OrderStore findById(int id) {
        OrderStore rsl = null;
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement(
                     "SELECT * FROM orders WHERE id = ?")) {
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                rsl = new OrderStore(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }


    public OrderStore findByName(String key) {
        OrderStore rsl = null;
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement(
                     "SELECT * FROM orders WHERE name = ?")) {
            pr.setString(1, key);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                rsl = new OrderStore(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp(4)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public int update(String key, int id) {
        int i = 0;
        OrderStore rsl = null;
        try (Connection con = pool.getConnection();
             PreparedStatement pr = con.prepareStatement(
                     "UPDATE orders SET name = ? WHERE id = ?")) {
            pr.setString(1, key);
            pr.setInt(2, id);
            i = pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
