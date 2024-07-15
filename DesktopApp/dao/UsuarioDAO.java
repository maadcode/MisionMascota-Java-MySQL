
package dao;

import dto.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.BD;

public class UsuarioDAO {
    
    private Connection dbConnection = null;

    public UsuarioDAO() {
        this.dbConnection = BD.getConnection();
    }
    
    public void create(UsuarioDTO usuario) {
        PreparedStatement ps;
        try {
            String sql = "INSERT INTO Usuarios(usuario, clave, idRol, nombres, apellidos, DNI)"+
                "VALUES(?, ?, ?, ?, ?, ?)";
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setInt(3, usuario.getRol());
            ps.setString(4, usuario.getNombres());
            ps.setString(5, usuario.getApellidos());
            ps.setString(6, usuario.getDNI());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<UsuarioDTO> read() {
        ArrayList<UsuarioDTO> listaUsuarios = new ArrayList<>();
        Statement st;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Usuarios";
            st = this.dbConnection.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                UsuarioDTO usuario = new UsuarioDTO(rs.getString("usuario"), rs.getString("clave"), rs.getInt("idRol"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("DNI"));
                listaUsuarios.add(usuario);
            }
            rs.close();
            st.close();
            return listaUsuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuarios;
    }
    
    public void update(UsuarioDTO usuario) {
        PreparedStatement ps;
        try {
            String sql = "UPDATE Usuarios SET clave = ?, idRol = ?, nombres = ?, apellidos = ?, DNI = ? WHERE usuario = ?";
            ps = this.dbConnection.prepareStatement(sql);
            
            ps.setString(1, usuario.getPassword());
            ps.setInt(2, usuario.getRol());
            ps.setString(3, usuario.getNombres());
            ps.setString(4, usuario.getApellidos());
            ps.setString(5, usuario.getDNI());
            ps.setString(6, usuario.getUsername());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String username) {
        PreparedStatement ps;
        try {
            String sql = "DELETE FROM Usuarios WHERE usuario = ?";
            ps = this.dbConnection.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public UsuarioDTO getUsuario(String username) {
        UsuarioDTO usuario = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Usuarios WHERE usuario = ?";
            ps = this.dbConnection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) {
                usuario = new UsuarioDTO(rs.getString("usuario"), rs.getString("clave"), rs.getInt("idRol"), rs.getString("nombres"), rs.getString("apellidos"), rs.getString("DNI"));
            }
            rs.close();
            ps.close();
            return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
}
