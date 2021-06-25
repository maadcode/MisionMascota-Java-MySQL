
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
import models.MascotaModel;
import utils.BD;

public class MascotaDAO implements ICRUD {
    
    private Connection dbConnection = null;
    private String sql = null;
    private Statement dbQ;
    private MascotaModel mascotaModel;

    public MascotaDAO() {
    }

    public MascotaDAO(MascotaModel mascotaModel) {
        this.mascotaModel = mascotaModel;
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
    }

    @Override
    public void delete(String id) {
    }
    
    public ArrayList<MascotaModel> getListaMascotas() throws SQLException {
        ArrayList<MascotaModel> listaMascotas = new ArrayList<MascotaModel>();
        
        try {
            ResultSet rs = read();
            MascotaModel mascota;
            
            while(rs.next()) {
                mascota = new MascotaModel(rs.getString("nombreMascota"), rs.getFloat("peso"), rs.getString("raza"), rs.getString("fechaNac"), rs.getString("fechaIng"), rs.getInt("idEstadoMascota"));
                listaMascotas.add(mascota);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return listaMascotas;
    }
    
}
