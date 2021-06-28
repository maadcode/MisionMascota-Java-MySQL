
package controllers;

import dao.AdopcionesDAO;
import dao.MascotaDAO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import models.AdopcionesModel;
import models.MascotaModel;
import views.Dashboard;
import views.Menu;

public class DashboardController {
    private AdopcionesDAO adopcionesDAO;
    private MascotaDAO mascotasDAO;
    private Dashboard dashboard;
    private Menu menu;

    public DashboardController(AdopcionesDAO adopcionesDAO, MascotaDAO mascotasDAO, Dashboard dashboard, Menu menu) {
        this.adopcionesDAO = adopcionesDAO;
        this.mascotasDAO = mascotasDAO;
        this.dashboard = dashboard;
        this.menu = menu;
        
        this.dashboard.setSize(750, 520);
        this.menu.contentPanel.removeAll();
        this.menu.contentPanel.add(this.dashboard);
        this.menu.contentPanel.repaint();
        this.menu.lblTitle.setText("Dashboard");
        
        getAdopcionesMensual();
        getMascotasDisponibles();
        listarAdopcionesRecientes();
        listarMascotasRecientes();
    }

    private void getAdopcionesMensual() {
        int adopcionesMensual = this.adopcionesDAO.getAdopcionesMensual();
        this.dashboard.lblAdopciones.setText(adopcionesMensual+"");
    }

    private void getMascotasDisponibles() {
        int mascotasDisponibles = this.mascotasDAO.getMascotasDisponibles();
        this.dashboard.lblMascotas.setText(mascotasDisponibles+"");
    }
    
    private void listarAdopcionesRecientes() {
        ArrayList<AdopcionesModel> list;
        list = this.adopcionesDAO.getListaAdopcionesRecientes();
        DefaultTableModel table = (DefaultTableModel) dashboard.tblAdopciones.getModel();
        // Clean table
        table.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdAdopcion();
            row[1] = list.get(i).getIdAdoptante();
            row[2] = list.get(i).getIdMascota();
            row[3] = list.get(i).getFechaAdop();
            
            table.addRow(row);
        }
    }

    private void listarMascotasRecientes() {
        ArrayList<MascotaModel> list;
        list = this.mascotasDAO.getListaMascotasRecientes();
        DefaultTableModel table = (DefaultTableModel) dashboard.tblMascotas.getModel();
        // Clean table
        table.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdMascota();
            row[1] = list.get(i).getNombre();
            row[2] = list.get(i).getFechaIngreso();
            row[3] = list.get(i).getEstadoMascota();
            
            table.addRow(row);
        }
    }
}
