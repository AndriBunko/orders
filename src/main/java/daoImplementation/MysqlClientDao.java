package daoImplementation;

import dao.ClientDao;
import entity.Client;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by Adrew on 03.09.2017.
 */
public class MysqlClientDao implements ClientDao {
    Connection conn;

    public MysqlClientDao(Connection conn) throws SQLException {
        this.conn = conn;
        initDB();
    }

    private void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("CREATE TABLE IF NOT EXISTS Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT NOT NULL)");
        } finally {
            st.close();
        }
    }

    public void addClient(Scanner sc) throws SQLException{
        System.out.print("Enter client name: ");
        String name = sc.nextLine();
        System.out.print("Enter client age: ");
        int age = Integer.parseInt(sc.nextLine());

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Clients (name, age) VALUES(?, ?)");
        try {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        }
        finally {
            ps.close();
        }
    }

    public Client getClientById(Scanner sc) throws SQLException {
        System.out.print("Enter client id: ");
        int id = Integer.parseInt(sc.nextLine());

        Client client;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM clients WHERE id = ?");
        try {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            client = new Client();
            try {
                rs.next();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setAge(rs.getInt("age"));
            }
            finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
        return client;
    }
}
