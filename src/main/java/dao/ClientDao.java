package dao;

import entity.Client;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by andrew on 03.09.2017.
 */
public interface ClientDao {
    void addClient(Scanner sc) throws SQLException;
    Client getClientById(Scanner sc) throws SQLException;

}
