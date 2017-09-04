package daoImplementation;

import dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Andrew on 03.09.2017.
 */
public class MysqlDaoFactory implements DaoFactory {

    private static final String dbConnection = "jdbc:mysql://localhost:3306/homeWork2";
    private static final String dbUser = "root";
    private static final String dbPassword = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbConnection, dbUser, dbPassword);
    }

    public ClientDao getClientDao(Connection conn) throws SQLException {
        return new MysqlClientDao(conn);
    }

    public OrderDao getOrderDao(Connection conn) throws SQLException {
        return new MysqlOrderDao(conn);
    }

    public ProductDao getProductDao(Connection conn) throws SQLException {
        return new MysqlProductDao(conn);
    }

    public OrderProductDao getOrderProductDao(Connection conn) throws SQLException {
        return new MysqlOrderProductDao(conn);
    }
}
