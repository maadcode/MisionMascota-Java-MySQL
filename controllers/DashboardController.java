
package controllers;

import dao.AdopcionDAO;
import dao.MascotaDAO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import dto.AdopcionDTO;
import dto.MascotaDTO;
import views.Dashboard;
import views.Menu;

public class DashboardController {
    private AdopcionDAO adopcionDAO;
    private MascotaDAO mascotasDAO;
    private Dashboard dashboard;
    private Menu menu;

    public DashboardController(AdopcionDAO adopcionDAO, MascotaDAO mascotasDAO, Dashboard dashboard, Menu menu) {
        this.adopcionDAO = adopcionDAO;
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
        int adopcionesMensual = this.adopcionDAO.getAdopcionesMensual();
        this.dashboard.lblAdopciones.setText(adopcionesMensual+"");
    }

    private void getMascotasDisponibles() {
        int mascotasDisponibles = this.mascotasDAO.getMascotasDisponibles();
        this.dashboard.lblMascotas.setText(mascotasDisponibles+"");
    }
    
    private void listarAdopcionesRecientes() {
        ArrayList<AdopcionDTO> list = this.adopcionDAO.getListaAdopcionesRecientes();
        DefaultTableModel table = (DefaultTableModel) this.dashboard.tblAdopciones.getModel();
        // Clean table
        table.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdAdopcion();
            row[1] = list.get(i).getNombreAdoptante();
            row[2] = list.get(i).getNombreMascota();
            row[3] = list.get(i).getFechaAdop();
            
            table.addRow(row);
        }
    }

    private void listarMascotasRecientes() {
        ArrayList<MascotaDTO> list = this.mascotasDAO.getListaMascotasRecientes();
        DefaultTableModel table = (DefaultTableModel) dashboard.tblMascotas.getModel();
        // Clean table
        table.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdMascota();
            row[1] = list.get(i).getNombre();
            row[2] = list.get(i).getFechaIngreso();
            if(list.get(i).getEstadoMascota() == 1) {
                row[3] = "Nuevo";
            } else if(list.get(i).getEstadoMascota() == 2) {
                row[3] = "Disponible";
            } else if(list.get(i).getEstadoMascota() == 3) {
                row[3] = "Adoptado";
            }
            
            table.addRow(row);
        }
    }
}
