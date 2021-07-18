
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.BD;

public class UsuarioDAO {
    
    private Connection dbConnection = null;

    public UsuarioDAO() {
        this.dbConnection = new BD().getConnection();
    }
    
    public boolean checkLogin(String user, String password) {
        ResultSet rs;
        PreparedStatement ps;
        boolean result = false;
        try {
            String sql = "SELECT * FROM Usuarios WHERE usuario = ? AND password = ?";
            ps = this.dbConnection.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()) {
                result = true;
            }
            rs.close();
            ps.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
