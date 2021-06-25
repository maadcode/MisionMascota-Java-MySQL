
package controllers;

import dao.MascotaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import models.MascotaModel;
import views.Menu;
import views.ViewMascotas;

public class MascotasController implements ActionListener {
    private MascotaDAO mascotaDAO;
    private ViewMascotas mascotaView;
    private Menu menu;

    public MascotasController(MascotaDAO mascotaDAO, ViewMascotas mascotaView, Menu menu) {
        this.mascotaDAO = mascotaDAO;
        this.mascotaView = mascotaView;
        this.menu = menu;
        
        this.mascotaView.setSize(750, 520);
        this.menu.contentPanel.removeAll();
        this.menu.contentPanel.add(this.mascotaView);
        this.menu.contentPanel.repaint();
        this.menu.lblTitle.setText("Mascotas");
        
        listarMascotas();
        this.mascotaView.btnBuscarMascota.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(mascotaView.btnBuscarMascota)) {
        }
    }
    
    public void listarMascotas() {
        ArrayList<MascotaModel> list;
        try {
            list = this.mascotaDAO.getListaMascotas();
            
            DefaultTableModel table = (DefaultTableModel) mascotaView.tblMascotas.getModel();
            // Clean table
            table.setRowCount(0);
            
            Object[] row = new Object[3];
            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getNombre();
                row[1] = list.get(i).getRaza();
                row[2] = list.get(i).getEstadoMascota();

                table.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdoptantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
