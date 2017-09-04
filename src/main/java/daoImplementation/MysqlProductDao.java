package daoImplementation;

import dao.ProductDao;
import entity.Product;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by Andrew on 03.09.2017.
 */
public class MysqlProductDao implements ProductDao {
    private Connection conn;

    public MysqlProductDao(Connection conn) throws SQLException {
        this.conn = conn;
        initDB();
    }

    public void addProduct(Scanner sc) throws SQLException {
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product price: ");
        int price = Integer.parseInt(sc.nextLine());

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Products (name, price) VALUES(?, ?)");
        try {
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

    public Product getProductById(Scanner sc) throws SQLException {
        System.out.print("Enter product price: ");
        int id = Integer.parseInt(sc.nextLine());

        Product product;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products WHERE id = ?");
        try {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            product = new Product();
            try {
                rs.next();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
            }
            finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
        return product;
    }

    private void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("CREATE TABLE IF NOT EXISTS products (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL, price INT NOT NULL)");
        } finally {
            st.close();
        }
    }
}
