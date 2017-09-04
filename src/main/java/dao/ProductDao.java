package dao;

import entity.Product;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Andrew on 03.09.2017.
 */
public interface ProductDao {
    void addProduct(Scanner sc) throws SQLException;
    Product getProductById(Scanner sc) throws SQLException;
}
