package dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Andrew on 03.09.2017.
 */
public interface DaoFactory {
    ClientDao getClientDao(Connection conn) throws SQLException;
    OrderDao getOrderDao(Connection conn) throws SQLException;
    ProductDao getProductDao(Connection conn) throws SQLException;
    OrderProductDao getOrderProductDao(Connection conn) throws SQLException;
}
