
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dto.AdoptanteDTO;
import services.BD;

public class AdoptanteDAO {
    
    private Connection dbConnection = null;
    
    public AdoptanteDAO() {
        this.dbConnection = new BD().getConnection();
    }

    public void create(AdoptanteDTO adoptante) {
        PreparedStatement ps;
        try {
            String sql = "INSERT INTO Adoptantes(nombreAdoptante, apellidoAdoptante, DNI, edad, telefono, direccion, correo, propietarioCasa, permisoDepa)"+
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setString(1, adoptante.getNombre());
            ps.setString(2, adoptante.getApellido());
            ps.setString(3, adoptante.getDNI());
            ps.setString(4, adoptante.getFechaNacimiento());
            ps.setString(5, adoptante.getTelefono());
            ps.setString(6, adoptante.getDireccion());
            ps.setString(7, adoptante.getCorreo());
            ps.setString(8, adoptante.getPropietario());
            ps.setString(9, adoptante.getPermiso());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<AdoptanteDTO> read() {
        ArrayList<AdoptanteDTO> listaAdoptantes = new ArrayList<>();
        Statement st;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Adoptantes";
            st = this.dbConnection.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                AdoptanteDTO adoptante = new AdoptanteDTO(rs.getInt("idAdoptante"), rs.getString("nombreAdoptante"), rs.getString("apellidoAdoptante"), rs.getString("DNI"), rs.getString("fechaNac"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("correo"), rs.getString("propietarioCasa"), rs.getString("permisoDepa"));
                listaAdoptantes.add(adoptante);
            }
            rs.close();
            st.close();
            return listaAdoptantes;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAdoptantes;
    }
    
    public void update(AdoptanteDTO adoptante) {
        PreparedStatement ps;
        try {
            String sql = "UPDATE Adoptantes SET nombreAdoptante = ?, apellidoAdoptante = ?, DNI = ?, edad = ?, telefono = ?, direccion = ?, correo = ?, propietarioCasa = ?, permisoDepa = ? WHERE idAdoptante = ?";
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setString(1, adoptante.getNombre());
            ps.setString(2, adoptante.getApellido());
            ps.setString(3, adoptante.getDNI());
            ps.setString(4, adoptante.getFechaNacimiento());
            ps.setString(5, adoptante.getTelefono());
            ps.setString(6, adoptante.getDireccion());
            ps.setString(7, adoptante.getCorreo());
            ps.setString(8, adoptante.getPropietario());
            ps.setString(9, adoptante.getPermiso());
            ps.setInt(10, adoptante.getIdAdoptante());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        PreparedStatement ps;
        try {
            String sql = "DELETE FROM Adoptantes WHERE idAdoptante = ?";
            ps = this.dbConnection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AdoptanteDTO getAdoptante(int id) {
        AdoptanteDTO adoptante = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Adoptantes WHERE idAdoptante = ?";
            ps = this.dbConnection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                adoptante = new AdoptanteDTO(rs.getInt("idAdoptante"), rs.getString("nombreAdoptante"), rs.getString("apellidoAdoptante"), rs.getString("DNI"), rs.getString("fechaNac"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("correo"), rs.getString("propietarioCasa"), rs.getString("permisoDepa"));
            }
            rs.close();
            ps.close();
            return adoptante;
        } catch (SQLException ex) {
            Logger.getLogger(AdoptanteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adoptante;
    }
}
