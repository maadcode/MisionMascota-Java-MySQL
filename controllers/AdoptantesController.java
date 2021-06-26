
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
        this.adoptanteView.btnAgregarAdoptante.addActionListener(this);
        this.adoptanteView.btnEditarAdoptante.addActionListener(this);
        this.adoptanteView.btnEliminarAdoptante.addActionListener(this);
        listarAdoptantes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(adoptanteView.btnBuscarAdoptante)) {
            if(this.adoptanteView.txtCodigo.getText() != null) {
                buscarAdoptante(this.adoptanteView.txtCodigo.getText());
            }
        }
        if(e.getSource().equals(adoptanteView.btnAgregarAdoptante)) {
            agregarAdoptante();
        }
        if(e.getSource().equals(adoptanteView.btnEditarAdoptante)) {
            if(this.adoptanteView.txtCodigo.getText() != null) {
                editarAdoptante(this.adoptanteView.txtCodigo.getText());
            }
        }
        if(e.getSource().equals(adoptanteView.btnEliminarAdoptante)) {
            if(this.adoptanteView.txtCodigo.getText() != null) {
                eliminarAdoptante(this.adoptanteView.txtCodigo.getText());
            }
        }
    }
    
    public void listarAdoptantes() {
        ArrayList<AdoptanteModel> list;
        list = this.adoptanteDAO.getListaAdoptantes();
        DefaultTableModel table = (DefaultTableModel) adoptanteView.tblAdoptantes.getModel();
        // Clean table
        table.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdAdoptante();
            row[1] = list.get(i).getNombre();
            row[2] = list.get(i).getApellido();
            row[3] = list.get(i).getDNI();
            
            table.addRow(row);
        }
    }

    private void agregarAdoptante() {
        this.adoptanteDAO.adoptanteModel.setNombre(this.adoptanteView.txtNombreAdoptante.getText());
        this.adoptanteDAO.adoptanteModel.setApellido(this.adoptanteView.txtApellidoAdoptante.getText());
        this.adoptanteDAO.adoptanteModel.setDNI(this.adoptanteView.txtDNI.getText());
        this.adoptanteDAO.adoptanteModel.setEdad(Integer.parseInt(this.adoptanteView.txtEdadAdoptante.getText()));
        this.adoptanteDAO.adoptanteModel.setTelefono(this.adoptanteView.txtTelefono.getText());
        this.adoptanteDAO.adoptanteModel.setDireccion(this.adoptanteView.txtDireccion.getText());
        this.adoptanteDAO.adoptanteModel.setCorreo(this.adoptanteView.txtCorreo.getText());
        if(this.adoptanteView.cbxPropietario.getSelectedIndex() == 0) {
            this.adoptanteDAO.adoptanteModel.setPropietario("SI");
        } else {
            this.adoptanteDAO.adoptanteModel.setPropietario("NO");
        }
        if(this.adoptanteView.cbxPermiso.getSelectedIndex() == 0) {
            this.adoptanteDAO.adoptanteModel.setPermiso("SI");
        } else {
            this.adoptanteDAO.adoptanteModel.setPermiso("NO");
        }

        this.adoptanteDAO.create();
        listarAdoptantes();
    }

    private void buscarAdoptante(String id) {
        AdoptanteModel adoptante = this.adoptanteDAO.getAdoptante(id);
        this.adoptanteView.txtNombreAdoptante.setText(adoptante.getNombre());
        this.adoptanteView.txtApellidoAdoptante.setText(adoptante.getApellido());
        this.adoptanteView.txtDNI.setText(adoptante.getDNI());
        this.adoptanteView.txtEdadAdoptante.setText(adoptante.getEdad()+"");
        this.adoptanteView.txtCorreo.setText(adoptante.getCorreo());
        this.adoptanteView.txtDireccion.setText(adoptante.getDireccion());
        this.adoptanteView.txtTelefono.setText(adoptante.getTelefono());
        if(adoptante.getPropietario() == "SI") {
            this.adoptanteView.cbxPropietario.setSelectedIndex(0);
        } else {
            this.adoptanteView.cbxPropietario.setSelectedIndex(1);
        }
        if(adoptante.getPermiso()== "SI") {
            this.adoptanteView.cbxPermiso.setSelectedIndex(0);
        } else {
            this.adoptanteView.cbxPermiso.setSelectedIndex(1);
        }
    }

    private void editarAdoptante(String id) {
        this.adoptanteDAO.adoptanteModel.setNombre(this.adoptanteView.txtNombreAdoptante.getText());
        this.adoptanteDAO.adoptanteModel.setApellido(this.adoptanteView.txtApellidoAdoptante.getText());
        this.adoptanteDAO.adoptanteModel.setDNI(this.adoptanteView.txtDNI.getText());
        this.adoptanteDAO.adoptanteModel.setEdad(Integer.parseInt(this.adoptanteView.txtEdadAdoptante.getText()));
        this.adoptanteDAO.adoptanteModel.setTelefono(this.adoptanteView.txtTelefono.getText());
        this.adoptanteDAO.adoptanteModel.setDireccion(this.adoptanteView.txtDireccion.getText());
        this.adoptanteDAO.adoptanteModel.setCorreo(this.adoptanteView.txtCorreo.getText());
        if(this.adoptanteView.cbxPropietario.getSelectedIndex() == 0) {
            this.adoptanteDAO.adoptanteModel.setPropietario("SI");
        } else {
            this.adoptanteDAO.adoptanteModel.setPropietario("NO");
        }
        if(this.adoptanteView.cbxPermiso.getSelectedIndex() == 0) {
            this.adoptanteDAO.adoptanteModel.setPermiso("SI");
        } else {
            this.adoptanteDAO.adoptanteModel.setPermiso("NO");
        }

        this.adoptanteDAO.update(id);
        listarAdoptantes();
    }

    private void eliminarAdoptante(String id) {
        this.adoptanteDAO.delete(id);
        listarAdoptantes();
    }
}
