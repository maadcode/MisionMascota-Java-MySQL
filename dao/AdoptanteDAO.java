
package dao;

import interfaces.ICRUD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.AdoptanteModel;
import models.UsuarioModel;
import utils.BD;

public class AdoptanteDAO implements ICRUD {
    
    private Connection dbConnection = null;
    private String sql = null;
    private Statement dbQ;
    private AdoptanteModel adoptanteModel;

    public AdoptanteDAO() {
    }
    
    public AdoptanteDAO(AdoptanteModel adoptanteModel) {
        this.adoptanteModel = adoptanteModel;
        this.dbConnection = new BD().getConnection();
        try {
            this.dbQ = this.dbConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create() {
    }

    @Override
    public ResultSet read() {
        ResultSet rs = null;
        try {
            sql = "SELECT * FROM Adoptantes";
            rs = this.dbQ.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    @Override
    public void update(String id) {
    }

    @Override
    public void delete(String id) {
    }
    
    public ArrayList<AdoptanteModel> getListaAdoptantes() throws SQLException {
        ArrayList<AdoptanteModel> listaAdoptantes = new ArrayList<AdoptanteModel>();
        
        try {
            ResultSet rs = read();
            AdoptanteModel adoptante;
            
            while(rs.next()) {
                adoptante = new AdoptanteModel(rs.getString("nombreAdoptante"), rs.getString("apellidoAdoptante"), rs.getString("DNI"), rs.getInt("edad"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("propietarioCasa"), rs.getString("permisoDepa"));
                listaAdoptantes.add(adoptante);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return listaAdoptantes;
    }
    
}
