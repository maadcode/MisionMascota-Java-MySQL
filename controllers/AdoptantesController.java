
package controllers;

import dao.AdoptanteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import models.AdoptanteModel;
import views.Menu;
import views.ViewAdoptantes;

public class AdoptantesController implements ActionListener {
    private AdoptanteDAO adoptanteDAO;
    private ViewAdoptantes adoptanteView;
    private Menu menu;

    public AdoptantesController(AdoptanteDAO adoptanteDAO, ViewAdoptantes adoptanteView, Menu menu) {
        this.adoptanteDAO = adoptanteDAO;
        this.adoptanteView = adoptanteView;
        this.menu = menu;
        
        this.adoptanteView.setSize(750, 520);
        this.menu.contentPanel.removeAll();
        this.menu.contentPanel.add(this.adoptanteView);
        this.menu.contentPanel.repaint();
        this.menu.lblTitle.setText("Adoptantes");
        
        this.adoptanteView.btnBuscarAdoptante.addActionListener(this);
        listarAdoptantes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(adoptanteView.btnBuscarAdoptante)) {
            
        }
    }
    
    public void listarAdoptantes() {
        ArrayList<AdoptanteModel> list;
        try {
            list = this.adoptanteDAO.getListaAdoptantes();
            
            DefaultTableModel table = (DefaultTableModel) adoptanteView.tblAdoptantes.getModel();
            // Clean table
            table.setRowCount(0);
            
            Object[] row = new Object[3];
            for(int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getNombre();
                row[1] = list.get(i).getApellido();
                row[2] = list.get(i).getDNI();

                table.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdoptantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
