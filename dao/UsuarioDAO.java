
package dao;

import interfaces.ICRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UsuarioModel;
import services.BD;

public class UsuarioDAO {
    
    private Connection dbConnection = null;
    private String sql = null;
    private Statement dbQ;
    public UsuarioModel usuarioModel;

    public UsuarioDAO() {
        this.dbConnection = new BD().getConnection();
        try {
            this.dbQ = this.dbConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkLogin(String user, String password) {
        ResultSet rs;
        PreparedStatement ps;
        sql = "SELECT * FROM Usuarios WHERE usuario = ? AND password = ?";
        
        try {
            ps = this.dbConnection.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
