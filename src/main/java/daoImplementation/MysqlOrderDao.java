package daoImplementation;

import dao.OrderDao;
import entity.Client;
import entity.Order;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by Andrew on 03.09.2017.
 */
public class MysqlOrderDao implements OrderDao {

    Connection conn;

    public MysqlOrderDao(Connection conn) throws SQLException {
        this.conn = conn;
        initDB();
    }

    private void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("CREATE TABLE IF NOT EXISTS Orders (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, clientId INT NOT NULL, price INT)");
        } finally {
        st.close();
    }
    }
    public void createOrder(Client client) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Orders (clientId, price) VALUES(?, ?)");
        try {
            ps.setInt(1, client.getId() );
            ps.setInt(2,0);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

    public Order getOrderById(int id) throws SQLException {
        Order order;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Orders WHERE id = ?");
        try {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            order = new Order();
            try {
                rs.next();
                order.setId(rs.getInt("id"));
                order.setClientId(rs.getInt("clientId"));
                order.setPrice(rs.getInt("price"));
            }
            finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
        return order;
    }

    public void updateOrder() throws SQLException {

    }
}
