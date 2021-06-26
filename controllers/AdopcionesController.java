
package controllers;

import dao.AdopcionesDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import models.AdopcionesModel;
import views.Menu;
import views.ViewAdopciones;

public class AdopcionesController implements ActionListener {
    private AdopcionesDAO adopcionesDAO;
    private ViewAdopciones adopcionesView;
    private Menu menu;

    public AdopcionesController(AdopcionesDAO adopcionesDAO, ViewAdopciones adopcionesView, Menu menu) {
        this.adopcionesDAO = adopcionesDAO;
        this.adopcionesView = adopcionesView;
        this.menu = menu;
        
        this.adopcionesView.setSize(750, 520);
        this.menu.contentPanel.removeAll();
        this.menu.contentPanel.add(this.adopcionesView);
        this.menu.contentPanel.repaint();
        this.menu.lblTitle.setText("Adopciones");
        
        this.adopcionesView.btnBuscarAdopcion.addActionListener(this);
        listarAdopciones();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(adopcionesView.btnBuscarAdopcion)) {
            
        }
    }
    
    public void listarAdopciones() {
        ArrayList<AdopcionesModel> list;
        try {
            list = this.adopcionesDAO.getListaAdopciones();
            
            DefaultTableModel table = (DefaultTableModel) adopcionesView.tblAdopciones.getModel();
            // Clean table
            table.setRowCount(0);
            
            Object[] row = new Object[3];
            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getIdAdoptante();
                row[1] = list.get(i).getIdMascota();
                row[2] = list.get(i).getFechaAdop();

                table.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdoptantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
