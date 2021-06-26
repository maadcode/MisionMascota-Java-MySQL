
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
import models.AdoptanteModel;
import services.BD;

public class AdoptanteDAO implements ICRUD {
    
    private Connection dbConnection = null;
    private String sql = null;
    public AdoptanteModel adoptanteModel;

    public AdoptanteDAO() {
    }
    
    public AdoptanteDAO(AdoptanteModel adoptanteModel) {
        this.adoptanteModel = adoptanteModel;
        this.dbConnection = new BD().getConnection();
    }

    @Override
    public void create() {
        PreparedStatement ps;
        try {
            sql = "INSERT INTO Adoptantes(nombreAdoptante, apellidoAdoptante, DNI, edad, telefono, direccion, correo, propietarioCasa, permisoDepa)"+
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setString(1, this.adoptanteModel.getNombre());
            ps.setString(2, this.adoptanteModel.getApellido());
            ps.setString(3, this.adoptanteModel.getDNI());
            ps.setInt(4, this.adoptanteModel.getEdad());
            ps.setString(5, this.adoptanteModel.getTelefono());
            ps.setString(6, this.adoptanteModel.getDireccion());
            ps.setString(7, this.adoptanteModel.getCorreo());
            ps.setString(8, this.adoptanteModel.getPropietario());
            ps.setString(9, this.adoptanteModel.getPermiso());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public ResultSet read() {
        ResultSet rs = null;
        Statement dbQ;
        try {
            dbQ = this.dbConnection.createStatement();
            sql = "SELECT * FROM Adoptantes";
            rs = dbQ.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    @Override
    public void update(String id) {
        PreparedStatement ps;
        try {
            sql = "UPDATE Adoptantes SET nombreAdoptante = ?, apellidoAdoptante = ?, DNI = ?, edad = ?, telefono = ?, direccion = ?, correo = ?, propietarioCasa = ?, permisoDepa = ? WHERE idAdoptante = "+id;
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setString(1, this.adoptanteModel.getNombre());
            ps.setString(2, this.adoptanteModel.getApellido());
            ps.setString(3, this.adoptanteModel.getDNI());
            ps.setInt(4, this.adoptanteModel.getEdad());
            ps.setString(5, this.adoptanteModel.getTelefono());
            ps.setString(6, this.adoptanteModel.getDireccion());
            ps.setString(7, this.adoptanteModel.getCorreo());
            ps.setString(8, this.adoptanteModel.getPropietario());
            ps.setString(9, this.adoptanteModel.getPermiso());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        PreparedStatement ps;
        try {
            sql = "DELETE FROM Adoptantes WHERE idAdoptante = "+id;
            ps = this.dbConnection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<AdoptanteModel> getListaAdoptantes() {
        ArrayList<AdoptanteModel> listaAdoptantes = new ArrayList<>();
        
        try {
            ResultSet rs = read();
            AdoptanteModel adoptante;
            
            while(rs.next()) {
                adoptante = new AdoptanteModel(rs.getInt("idAdoptante"), rs.getString("nombreAdoptante"), rs.getString("apellidoAdoptante"), rs.getString("DNI"), rs.getInt("edad"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("correo"), rs.getString("propietarioCasa"), rs.getString("permisoDepa"));
                listaAdoptantes.add(adoptante);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return listaAdoptantes;
    }
    
    public AdoptanteModel getAdoptante(String id) {
        for(int i = 0; i <= getListaAdoptantes().size(); i++) {
            if(getListaAdoptantes().get(i).getIdAdoptante() == Integer.parseInt(id)) {
                return getListaAdoptantes().get(i);
            }
        }
        return null;
    }
}
