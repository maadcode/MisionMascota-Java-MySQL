
package dao;

import interfaces.ICRUD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.AdopcionesModel;
import models.AdoptanteModel;
import utils.BD;

public class AdopcionesDAO implements ICRUD {
    
    private Connection dbConnection = null;
    private String sql = null;
    private Statement dbQ;
    private AdopcionesModel adopcionModel;

    public AdopcionesDAO() {
    }

    public AdopcionesDAO(AdopcionesModel adopcionModel) {
        this.adopcionModel = adopcionModel;
        this.dbConnection = new BD().getConnection();
        try {
            this.dbQ = this.dbConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AdopcionesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create() {
    }

    @Override
    public ResultSet read() {
        ResultSet rs = null;
        try {
            sql = "SELECT * FROM Adopciones";
            rs = this.dbQ.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(AdopcionesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public void update(String id) {
    }

    @Override
    public void delete(String id) {
    }
    
    public ArrayList<AdopcionesModel> getListaAdopciones() throws SQLException {
        ArrayList<AdopcionesModel> listaAdopciones = new ArrayList<AdopcionesModel>();
        
        try {
            ResultSet rs = read();
            AdopcionesModel adopcion;
            
            while(rs.next()) {
                adopcion = new AdopcionesModel(rs.getInt("idAdoptante"), rs.getInt("idMascota"), rs.getString("fechaAdop"));
                listaAdopciones.add(adopcion);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return listaAdopciones;
    }
}
