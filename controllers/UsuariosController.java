
package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UsuarioModel;
import views.Login;

public class UsuariosController {
    
    private UsuarioModel userModel;

    public UsuariosController() {
        this.userModel = new UsuarioModel();
    }
    
    public void index() {
        new Login().setVisible(true);
    }
    
    public boolean checkLogin(String user, String password) {
        try {
            ResultSet rs = this.userModel.getUsuario(user);
            if(rs.next()) {
                if(rs.getString("password").equals(password)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
