
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BD {
    private static Connection con;
    
    public static Connection getConnection() {
        if(con == null) {
            String connectionURL = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=MisionMascota;"
                + "user=sa;"
                + "password=s;";

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                con = DriverManager.getConnection(connectionURL);
                System.out.println("Connected");
                return con;
            } catch (SQLException ex) {
                System.out.println("Not connected");
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
}
    