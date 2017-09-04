package daoImplementation;

import dao.OrderProductDao;
import entity.Order;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by andrew on 04.09.2017.
 */
public class MysqlOrderProductDao implements OrderProductDao{
    Connection conn;

    public MysqlOrderProductDao(Connection conn) throws SQLException {
        this.conn = conn;
        initDB();
    }

    public boolean addProductToOrder(Product product, Order order) throws SQLException {
        if (order == null || product == null) return false;
        PreparedStatement ps = conn.prepareStatement("INSERT INTO OrdersProducts (orderId, productId) VALUES(?, ?)");
        try {
            ps.setInt(1, order.getId());
            ps.setInt(1, product.getId());
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        finally {
        ps.close();
    }
        return  true;
    }

    private void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("CREATE TABLE IF NOT EXISTS OrdersProducts (orderId INT NOT NULL , productId INT)");
        } finally {
            st.close();
        }
    }
}
