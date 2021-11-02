
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dto.MascotaDTO;
import services.BD;

public class MascotaDAO {
    
    private Connection dbConnection = null;

    public MascotaDAO() {
        this.dbConnection = new BD().getConnection();
    }

    public void create(MascotaDTO mascota) {
        PreparedStatement ps;
        try {
            String sql = "INSERT INTO Mascotas(nombreMascota, raza, fechaNac, peso, idEstado, fechaIng, imagenMascota)"+
                "VALUES(?, ?, ?, ?, ?, convert(date, GETDATE()), ?)";
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getRaza());
            ps.setString(3, mascota.getFechaNacimiento());
            ps.setFloat(4, mascota.getPeso());
            ps.setInt(5, mascota.getEstadoMascota());
            ps.setString(6, mascota.getImageURL());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<MascotaDTO> read() {
        ArrayList<MascotaDTO> listaMascotas = new ArrayList<>();
        Statement st;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Mascotas";
            st = this.dbConnection.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                MascotaDTO mascota = new MascotaDTO(rs.getInt("idMascota"), rs.getString("nombreMascota"), rs.getFloat("peso"), rs.getString("raza"), rs.getString("fechaNac"), rs.getString("fechaIng"), rs.getInt("idEstado"), rs.getString("imagenMascota"));
                listaMascotas.add(mascota);
            }
            rs.close();
            st.close();
            return listaMascotas;
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMascotas;
    }

    public void update(MascotaDTO mascota) {
        PreparedStatement ps;
        try {
            String sql = "UPDATE Mascotas SET nombreMascota = ?, raza = ?, fechaNac = ?, peso = ?, idEstado = ?, imagenMascota = ? WHERE idMascota = ?";
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getRaza());
            ps.setString(3, mascota.getFechaNacimiento());
            ps.setFloat(4, mascota.getPeso());
            ps.setInt(5, mascota.getEstadoMascota());
            ps.setString(6, mascota.getImageURL());
            ps.setInt(7, mascota.getIdMascota());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        PreparedStatement ps;
        try {
            String sql = "DELETE FROM Mascotas WHERE idMascota = ?";
            ps = this.dbConnection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public MascotaDTO getMascota(int id) {
        MascotaDTO mascota = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Mascotas WHERE idMascota = ?";
            ps = this.dbConnection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                mascota = new MascotaDTO(rs.getInt("idMascota"), rs.getString("nombreMascota"), rs.getFloat("peso"), rs.getString("raza"), rs.getString("fechaNac"), rs.getString("fechaIng"), rs.getInt("idEstado"), rs.getString("imagenMascota"));
            }
            rs.close();
            ps.close();
            return mascota;
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mascota;
    }
    
    public ArrayList<MascotaDTO> getListaMascotasRecientes() {
        ArrayList<MascotaDTO> listaMascotas = new ArrayList<>();
        Statement st;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Mascotas WHERE MONTH(fechaIng) = MONTH(convert(date, GETDATE()))";
            st = this.dbConnection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                MascotaDTO mascota = new MascotaDTO(rs.getInt("idMascota"), rs.getString("nombreMascota"), rs.getFloat("peso"), rs.getString("raza"), rs.getString("fechaNac"), rs.getString("fechaIng"), rs.getInt("idEstado"), rs.getString("imagenMascota"));
                listaMascotas.add(mascota);
            }
            rs.close();
            st.close();
            return listaMascotas;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaMascotas;
    }
    
    public int getMascotasDisponibles() {
        int cantidad = 0;
        Statement st;
        ResultSet rs;
        try {
            String sql = "SELECT COUNT(*) FROM Mascotas WHERE idEstado = 2";
            st = this.dbConnection.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()) {
                cantidad = rs.getInt(1);
            }
            rs.close();
            st.close();
            return cantidad;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cantidad;
    }
    
    public ArrayList<MascotaDTO> getListaMascotasDisponibles() {
        ArrayList<MascotaDTO> listaMascotas = new ArrayList<>();
        Statement st;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Mascotas WHERE idEstado = 2";
            st = this.dbConnection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                MascotaDTO mascota = new MascotaDTO(rs.getInt("idMascota"), rs.getString("nombreMascota"), rs.getFloat("peso"), rs.getString("raza"), rs.getString("fechaNac"), rs.getString("fechaIng"), rs.getInt("idEstado"), rs.getString("imagenMascota"));
                listaMascotas.add(mascota);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaMascotas;
    }
    
    public ArrayList<MascotaDTO> getMascotasEstado(String estado) {
        ArrayList<MascotaDTO> listaMascotas = new ArrayList<>();
        Statement st;
        ResultSet rs;
        
        
        try {
            String sql = "exec sp_mascotas_estado '"+estado+"'";
            st = this.dbConnection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                MascotaDTO mascota = new MascotaDTO(rs.getInt("idMascota"), rs.getString("nombreMascota"), rs.getFloat("peso"), rs.getString("raza"), rs.getString("fechaNac"), rs.getString("fechaIng"), rs.getInt("idEstado"), rs.getString("imagenMascota"));
                listaMascotas.add(mascota);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaMascotas;
    }
    
    public void cambiarEstado(int idMascota, int idEstado) {
        PreparedStatement ps;
        try {
            String sql = "UPDATE Mascotas SET idEstado = ? WHERE idMascota = ?";
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setInt(1, idEstado);
            ps.setInt(2, idMascota);
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MascotaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
