
import dao.*;
import daoImplementation.MysqlDaoFactory;
import entity.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Andrew on 03.09.2017.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection conn = MysqlDaoFactory.getConnection();
        try {
            DaoFactory daoFactory = new MysqlDaoFactory();
            ClientDao cd = daoFactory.getClientDao(conn);
            ProductDao pd = daoFactory.getProductDao(conn);
            OrderDao od = daoFactory.getOrderDao(conn);
            OrderProductDao opd = daoFactory.getOrderProductDao(conn);
            while (true) {
                System.out.println("1: add client");
                System.out.println("2: add product");
                System.out.println("3: create order");
                System.out.println("4: add product to order");
                //System.out.println("5: view clients");
                System.out.print("-> ");

                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        cd.addClient(sc);
                        break;
                    case "2":
                        pd.addProduct(sc);
                        break;
                    case "3":
                        od.createOrder(cd.getClientById(sc));
                        break;
                    case "4":
                        opd.addProductToOrder(pd.getProductById(sc), od.getOrderById(1));
                        break;
                    //                case "5":
                    //                    viewClients();
                    //                    break;
                    default:
                        return;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (conn != null) conn.close();
        }
    }
}
