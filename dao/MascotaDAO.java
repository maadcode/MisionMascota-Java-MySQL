
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
import models.MascotaModel;
import services.BD;

public class MascotaDAO implements ICRUD {
    
    private Connection dbConnection = null;
    private String sql = null;
    private Statement dbQ;
    public MascotaModel mascotaModel;

    public MascotaDAO() {
    }

    public MascotaDAO(MascotaModel mascotaModel) {
        this.mascotaModel = mascotaModel;
        this.dbConnection = new BD().getConnection();
        try {
            this.dbQ = this.dbConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create() {
        PreparedStatement ps;
        try {
            sql = "INSERT INTO Mascotas(nombreMascota, raza, fechaNac, peso, idEstadoMascota, fechaIng, imagenMascota)"+
                "VALUES(?, ?, ?, ?, ?, CURDATE(), ?)";
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setString(1, this.mascotaModel.getNombre());
            ps.setString(2, this.mascotaModel.getRaza());
            ps.setString(3, this.mascotaModel.getFechaNacimiento());
            ps.setFloat(4, this.mascotaModel.getPeso());
            ps.setInt(5, this.mascotaModel.getEstadoMascota());
            ps.setString(6, this.mascotaModel.getImageURL());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ResultSet read() {
        ResultSet rs = null;
        try {
            sql = "SELECT * FROM Mascotas";
            rs = this.dbQ.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public void update(String id) {
        PreparedStatement ps;
        try {
            sql = "UPDATE Mascotas SET nombreMascota = ?, raza = ?, fechaNac = ?, peso = ?, idEstadoMascota = ?, imagenMascota = ? WHERE idMascota = "+id;
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setString(1, this.mascotaModel.getNombre());
            ps.setString(2, this.mascotaModel.getRaza());
            ps.setString(3, this.mascotaModel.getFechaNacimiento());
            ps.setFloat(4, this.mascotaModel.getPeso());
            ps.setInt(5, this.mascotaModel.getEstadoMascota());
            ps.setString(6, this.mascotaModel.getImageURL());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        PreparedStatement ps;
        try {
            sql = "DELETE FROM Mascotas WHERE idMascota = "+id;
            ps = this.dbConnection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<MascotaModel> getListaMascotas() {
        ArrayList<MascotaModel> listaMascotas = new ArrayList<>();
        
        try {
            ResultSet rs = read();
            MascotaModel mascota;
            
            while(rs.next()) {
                mascota = new MascotaModel(rs.getInt("idMascota"), rs.getString("nombreMascota"), rs.getFloat("peso"), rs.getString("raza"), rs.getString("fechaNac"), rs.getString("fechaIng"), rs.getInt("idEstadoMascota"), rs.getString("imagenMascota"));
                listaMascotas.add(mascota);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return listaMascotas;
    }
    
    public MascotaModel getMascota(String id) {
        for(int i = 0; i <= getListaMascotas().size(); i++) {
            if(getListaMascotas().get(i).getIdMascota()  == Integer.parseInt(id)) {
                return getListaMascotas().get(i);
            }
        }
        return null;
    }
    
    public ArrayList<MascotaModel> getListaMascotasRecientes() {
        ArrayList<MascotaModel> listaMascotas = new ArrayList<>();
        
        try {
            sql = "SELECT * FROM Mascotas WHERE MONTH(fechaIng) = MONTH(CURRENT_DATE())";
            ResultSet rs = this.dbQ.executeQuery(sql);
            MascotaModel mascota;
            
            while(rs.next()) {
                mascota = new MascotaModel(rs.getInt("idMascota"), rs.getString("nombreMascota"), rs.getFloat("peso"), rs.getString("raza"), rs.getString("fechaNac"), rs.getString("fechaIng"), rs.getInt("idEstadoMascota"), rs.getString("imagenMascota"));
                listaMascotas.add(mascota);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return listaMascotas;
    }
    
    public int getMascotasDisponibles() {
        int cantidad = 0;
        
        try {
            sql = "SELECT COUNT(*) FROM Mascotas WHERE idEstadoMascota = 2";
            ResultSet rs = this.dbQ.executeQuery(sql);
            if(rs.next()) {
                cantidad = rs.getInt(1);
            }
            return cantidad;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return cantidad;
    }
}
