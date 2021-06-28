
package dao;

import interfaces.ICRUD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.UsuarioModel;
import services.BD;

public class UsuarioDAO implements ICRUD {
    
    private Connection dbConnection = null;
    private String sql = null;
    public UsuarioModel usuarioModel;

    public UsuarioDAO() {
    }

    public UsuarioDAO(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
        this.dbConnection = new BD().getConnection();
    }

    @Override
    public void create() {
    }

    @Override
    public ResultSet read() {
        ResultSet rs = null;
        Statement dbQ;
        try {
            dbQ = this.dbConnection.createStatement();
            sql = "SELECT * FROM Usuarios";
            rs = dbQ.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public void update(String id) {
    }

    @Override
    public void delete(String id) {
    }
    
    public ArrayList<UsuarioModel> getListaUsuarios() {
        ArrayList<UsuarioModel> listaUsuarios = new ArrayList<>();
        
        try {
            ResultSet rs = read();
            UsuarioModel usuarioModel;
            
            while(rs.next()) {
                usuarioModel = new UsuarioModel(rs.getInt("idUsuario"), rs.getString("usuario"), rs.getString("password"));
                listaUsuarios.add(usuarioModel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return listaUsuarios;
    }
    
    public ResultSet getUsuario(String usuario) {
        ResultSet rs = null;
        Statement dbQ;
        try {
            dbQ = this.dbConnection.createStatement();
            // "SELECT * FROM Usuarios WHERE usuario = BINARY \""+usuario+"\"
            sql = "SELECT * FROM Usuarios";
            rs = dbQ.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public boolean checkLogin(String user, String password) {
        for(int i = 0; i <= getListaUsuarios().size(); i++) {
            if(getListaUsuarios().get(i).getUsername().equals(user)) {
                if(getListaUsuarios().get(i).getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
