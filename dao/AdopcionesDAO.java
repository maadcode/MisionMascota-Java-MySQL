
package dao;

import interfaces.ICRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.AdopcionesModel;
import services.BD;

public class AdopcionesDAO implements ICRUD {
    
    private Connection dbConnection = null;
    private String sql = null;
    private Statement dbQ;
    public AdopcionesModel adopcionModel;

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
        PreparedStatement ps;
        try {
            sql = "INSERT INTO Adopciones(idAdoptante, idMascota, fechaAdop)"+
                "VALUES(?, ?, CURDATE())";
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setInt(1, this.adopcionModel.getIdAdoptante());
            ps.setInt(2, this.adopcionModel.getIdMascota());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdopcionesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        PreparedStatement ps;
        try {
            sql = "UPDATE Adopciones SET idAdoptante = ?, idMascota = ?, fechaAdop = ? WHERE idAdopcion = "+id;
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setInt(1, this.adopcionModel.getIdAdoptante());
            ps.setInt(2, this.adopcionModel.getIdMascota());
            ps.setString(3, this.adopcionModel.getFechaAdop());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdopcionesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        PreparedStatement ps;
        try {
            sql = "DELETE FROM Adopciones WHERE idAdopcion = "+id;
            ps = this.dbConnection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdopcionesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<AdopcionesModel> getListaAdopciones() {
        ArrayList<AdopcionesModel> listaAdopciones = new ArrayList<>();
        
        try {
            ResultSet rs = read();
            AdopcionesModel adopcion;
            
            while(rs.next()) {
                adopcion = new AdopcionesModel(rs.getInt("idAdopcion"), rs.getInt("idAdoptante"), rs.getInt("idMascota"), rs.getString("fechaAdop"));
                listaAdopciones.add(adopcion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return listaAdopciones;
    }
    
    public AdopcionesModel getAdopcion(String id) {
        for(int i = 0; i <= getListaAdopciones().size(); i++) {
            if(getListaAdopciones().get(i).getIdAdopcion() == Integer.parseInt(id)) {
                return getListaAdopciones().get(i);
            }
        }
        return null;
    }
    
}
