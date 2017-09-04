package dao;

import entity.Client;
import entity.Order;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Andrew on 03.09.2017.
 */
public interface OrderDao {
    void createOrder(Client client)throws SQLException;
    Order getOrderById(int id) throws SQLException;
    void updateOrder() throws SQLException;
}
