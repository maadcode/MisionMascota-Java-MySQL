
package controllers;

import dao.AdopcionDAO;
import dao.MascotaDAO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import dto.AdopcionDTO;
import dto.MascotaDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import views.MenuAdministrador;
import views.ViewReportes;

public class ReportesController implements ActionListener {
    private AdopcionDAO adopcionDAO;
    private MascotaDAO mascotasDAO;
    private ViewReportes reportesView;
    private MenuAdministrador menu;

    public ReportesController(AdopcionDAO adopcionDAO, MascotaDAO mascotasDAO, ViewReportes reportesView, MenuAdministrador menu) {
        this.adopcionDAO = adopcionDAO;
        this.mascotasDAO = mascotasDAO;
        this.reportesView = reportesView;
        this.menu = menu;
        
        this.reportesView.setSize(750, 520);
        this.menu.contentPanel.removeAll();
        this.menu.contentPanel.add(this.reportesView);
        this.menu.contentPanel.repaint();
        this.menu.lblTitle.setText("Reportes");
        
        this.reportesView.btnBuscarAdopciones.addActionListener(this);
        this.reportesView.btnBuscarMascotas.addActionListener(this);
        this.reportesView.btnReporteAdopciones.addActionListener(this);
        this.reportesView.btnReporteMascotas.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(reportesView.btnBuscarAdopciones)) {
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String fechaInicio = dateFormat.format(reportesView.txtFechaInicio.getDate());
            String fechaFin = dateFormat.format(reportesView.txtFechaFin.getDate());
            
            if(fechaInicio != null && fechaFin != null) {
                listarAdopciones(fechaInicio, fechaFin);
            }
        }
        if(e.getSource().equals(reportesView.btnBuscarMascotas)) {
            listarMascotas(this.reportesView.cbxEstadoMascota.getSelectedItem().toString());
        }
        if(e.getSource().equals(reportesView.btnReporteAdopciones)) {
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String fechaInicio = dateFormat.format(reportesView.txtFechaInicio.getDate());
            String fechaFin = dateFormat.format(reportesView.txtFechaFin.getDate());
            
            if(fechaInicio != null && fechaFin != null) {
                imprimirAdopciones(fechaInicio, fechaFin);
            }
        }
        if(e.getSource().equals(reportesView.btnReporteMascotas)) {
            imprimirMascotas(this.reportesView.cbxEstadoMascota.getSelectedItem().toString());
        }
    }
    
    private void listarAdopciones(String fechaInicio, String fechaFin) {
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

    private void listarMascotas(String estado) {
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

    private void imprimirAdopciones(String fechaInicio, String fechaFin) {
        
    }

    private void imprimirMascotas(String estado) {
        
    }
}
