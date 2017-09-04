package dao;

import entity.Order;
import entity.Product;

import java.sql.SQLException;

/**
 * Created by Adrew on 04.09.2017.
 */
public interface OrderProductDao {
    boolean addProductToOrder(Product product, Order order) throws SQLException;
}
