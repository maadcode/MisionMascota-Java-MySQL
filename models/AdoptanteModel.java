
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.BD;

public class AdoptanteModel {
    private Connection dbConnection = null;
    private String sql = null;
    private Statement dbQ;

    public AdoptanteModel() {
        this.dbConnection = new BD().getConnection();
        try {
            this.dbQ = this.dbConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet getAdoptante(String idAdoptante) {
        try {
            sql = "SELECT * FROM Usuarios WHERE usuario = BINARY \""+idAdoptante+"\"";
            ResultSet rs = this.dbQ.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
