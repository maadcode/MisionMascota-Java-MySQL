
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
    public Connection getConnection() {
        String URL = "jdbc:mysql://localhost:3306/Veterinaria";
        String USER = "root";
        String PASSWORD = "Mysql2021$";
        
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected");
            return con;
        } catch (SQLException ex) {
            System.out.println("Not connected");
            return null;
        }
    }
}
