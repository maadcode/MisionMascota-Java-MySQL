
package controllers;

import dao.AdopcionesDAO;
import dao.AdoptanteDAO;
import dao.MascotaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.AdopcionesModel;
import models.AdoptanteModel;
import models.MascotaModel;
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
        
        this.adopcionesView.btnBuscarAdoptante.addActionListener(this);
        this.adopcionesView.btnBuscarMascota.addActionListener(this);
        
        this.adopcionesView.btnBuscarAdopcion.addActionListener(this);
        this.adopcionesView.btnAgregarAdopcion.addActionListener(this);
        this.adopcionesView.btnEditarAdopcion.addActionListener(this);
        this.adopcionesView.btnEliminarAdopcion.addActionListener(this);
        listarAdopciones();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(adopcionesView.btnBuscarAdoptante)) {
            mostrarAdoptante(adopcionesView.txtCodigoAdoptante.getText());
        }
        if(e.getSource().equals(adopcionesView.btnBuscarMascota)) {
            mostrarMascota(adopcionesView.txtCodigoMascota.getText());
        }
        if(e.getSource().equals(adopcionesView.btnBuscarAdopcion)) {
            buscarAdopcion(adopcionesView.txtCodigoAdopcion.getText());
        }
        if(e.getSource().equals(adopcionesView.btnAgregarAdopcion)) {
            agregarAdopcion();
        }
        if(e.getSource().equals(adopcionesView.btnEditarAdopcion)) {
            editarAdopcion(adopcionesView.txtCodigoAdopcion.getText());
        }
        if(e.getSource().equals(adopcionesView.btnEliminarAdopcion)) {
            eliminarMascota(adopcionesView.txtCodigoAdopcion.getText());
        }
    }
    
    private void agregarAdopcion() {
        this.adopcionesDAO.adopcionModel.setIdAdoptante(Integer.parseInt(this.adopcionesView.txtCodigoAdoptante.getText()));
        this.adopcionesDAO.adopcionModel.setIdMascota(Integer.parseInt(this.adopcionesView.txtCodigoMascota.getText()));
        
        this.adopcionesDAO.create();
        listarAdopciones();
    }
    
    private void buscarAdopcion(String id) {
        AdopcionesModel adopcion = this.adopcionesDAO.getAdopcion(id);
        if(adopcion != null) {
            this.adopcionesView.txtCodigoAdoptante.setText(adopcion.getIdAdoptante()+"");
            mostrarAdoptante(adopcion.getIdAdoptante()+"");
            this.adopcionesView.txtCodigoMascota.setText(adopcion.getIdMascota()+"");
            mostrarMascota(adopcion.getIdMascota()+"");

            Date fecha;
            try {
                fecha = new SimpleDateFormat("yyyy-MM-dd").parse(adopcion.getFechaAdop());
                this.adopcionesView.txtFechaAdopcion.setDate(fecha);        
            } catch (ParseException ex) {
                Logger.getLogger(MascotasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe la adopción con el id ingresado");
        }
    }
    
    private void editarAdopcion(String id) {
        this.adopcionesDAO.adopcionModel.setIdAdoptante(Integer.parseInt(this.adopcionesView.txtCodigoAdoptante.getText()));
        this.adopcionesDAO.adopcionModel.setIdMascota(Integer.parseInt(this.adopcionesView.txtCodigoMascota.getText()));
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        this.adopcionesDAO.adopcionModel.setFechaAdop(dateFormat.format(this.adopcionesView.txtFechaAdopcion.getDate()));        
        
        this.adopcionesDAO.update(id);
        listarAdopciones();
    }
    
    private void eliminarMascota(String id) {
        this.adopcionesDAO.delete(id);
        listarAdopciones();
    }
    
    public void listarAdopciones() {
        ArrayList<AdopcionesModel> list;
        list = this.adopcionesDAO.getListaAdopciones();
        DefaultTableModel table = (DefaultTableModel) adopcionesView.tblAdopciones.getModel();
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

    private void mostrarAdoptante(String id) {
        String result;
        AdoptanteModel adoptanteModel = new AdoptanteModel();
        AdoptanteDAO adoptanteDAO = new AdoptanteDAO(adoptanteModel);
        adoptanteModel = adoptanteDAO.getAdoptante(id);
        
        result = "Nombre : " + adoptanteModel.getNombre() +
                 "\nApellido : " + adoptanteModel.getApellido()+
                 "\nDNI : " + adoptanteModel.getDNI()+
                 "\nEdad : " + adoptanteModel.getEdad()+
                 "\nDirección : " + adoptanteModel.getDireccion();
        this.adopcionesView.txtAdoptanteResult.setText(result);
    }

    private void mostrarMascota(String id) {
        String result;
        MascotaModel mascotaModel = new MascotaModel();
        MascotaDAO mascotaDAO = new MascotaDAO(mascotaModel);
        mascotaModel = mascotaDAO.getMascota(id);
        
        result = "Nombre : " + mascotaModel.getNombre() +
                 "\nRaza : " + mascotaModel.getRaza() +
                 "\nFecha de Ingreso : " + mascotaModel.getFechaIngreso();
        this.adopcionesView.txtMascotaResult.setText(result);
    }
}
