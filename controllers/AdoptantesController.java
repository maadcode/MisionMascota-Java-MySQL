
package controllers;

import dao.AdoptanteDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dto.AdoptanteDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import views.MenuAsistente;
import views.ViewAdoptantes;

public class AdoptantesController implements ActionListener, KeyListener {
    private AdoptanteDAO adoptanteDAO;
    private ViewAdoptantes adoptanteView;
    private MenuAsistente menu;

    public AdoptantesController(AdoptanteDAO adoptanteDAO, ViewAdoptantes adoptanteView, MenuAsistente menu) {
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
        this.adoptanteView.tblAdoptantes.addKeyListener(this);
        listarAdoptantes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(adoptanteView.btnBuscarAdoptante)) {
            if(this.adoptanteView.txtCodigoAdoptante.getText() != null) {
                buscarAdoptante(this.adoptanteView.txtCodigoAdoptante.getText());
            }
        }
        if(e.getSource().equals(adoptanteView.btnAgregarAdoptante)) {
            agregarAdoptante();
        }
        if(e.getSource().equals(adoptanteView.btnEditarAdoptante)) {
            if(this.adoptanteView.txtCodigoAdoptante.getText() != null) {
                editarAdoptante(this.adoptanteView.txtCodigoAdoptante.getText());
            }
        }
        if(e.getSource().equals(adoptanteView.btnEliminarAdoptante)) {
            if(this.adoptanteView.txtCodigoAdoptante.getText() != null) {
                eliminarAdoptante(this.adoptanteView.txtCodigoAdoptante.getText());
            }
        }
    }
    
    public void listarAdoptantes() {
        ArrayList<AdoptanteDTO> list = this.adoptanteDAO.read();
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
        AdoptanteDTO adoptante = new AdoptanteDTO();
        adoptante.setNombre(this.adoptanteView.txtNombreAdoptante.getText());
        adoptante.setApellido(this.adoptanteView.txtApellidoAdoptante.getText());
        adoptante.setDNI(this.adoptanteView.txtDNI.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        adoptante.setFechaNacimiento(dateFormat.format(this.adoptanteView.txtFechaNac.getDate()));
        
        adoptante.setTelefono(this.adoptanteView.txtTelefono.getText());
        adoptante.setDireccion(this.adoptanteView.txtDireccion.getText());
        adoptante.setCorreo(this.adoptanteView.txtCorreo.getText());
        adoptante.setPropietario(this.adoptanteView.cbxPropietario.getSelectedIndex());
        adoptante.setPermiso(this.adoptanteView.cbxPermiso.getSelectedIndex());
        this.adoptanteDAO.create(adoptante);
        listarAdoptantes();
    }

    private void buscarAdoptante(String id) {
        AdoptanteDTO adoptante = this.adoptanteDAO.getAdoptante(Integer.parseInt(id));
        if(adoptante != null) {
            this.adoptanteView.txtCodigoAdoptante.setText(adoptante.getIdAdoptante()+"");
            this.adoptanteView.txtNombreAdoptante.setText(adoptante.getNombre());
            this.adoptanteView.txtApellidoAdoptante.setText(adoptante.getApellido());
            this.adoptanteView.txtDNI.setText(adoptante.getDNI());
            
            Date fecha;
            try {
                fecha = new SimpleDateFormat("yyyy-MM-dd").parse(adoptante.getFechaNacimiento());
                this.adoptanteView.txtFechaNac.setDate(fecha);        
            } catch (ParseException ex) {
                Logger.getLogger(AdoptantesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.adoptanteView.txtCorreo.setText(adoptante.getCorreo());
            this.adoptanteView.txtDireccion.setText(adoptante.getDireccion());
            this.adoptanteView.txtTelefono.setText(adoptante.getTelefono());
            this.adoptanteView.cbxPropietario.setSelectedIndex(adoptante.getPropietario());
            this.adoptanteView.cbxPermiso.setSelectedIndex(adoptante.getPermiso());
        } else {
            JOptionPane.showMessageDialog(null, "No existe el adoptante con el id ingresado");
        }
    }

    private void editarAdoptante(String id) {
        AdoptanteDTO adoptante = new AdoptanteDTO();
        adoptante.setIdAdoptante(Integer.parseInt(id));
        adoptante.setNombre(this.adoptanteView.txtNombreAdoptante.getText());
        adoptante.setApellido(this.adoptanteView.txtApellidoAdoptante.getText());
        adoptante.setDNI(this.adoptanteView.txtDNI.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        adoptante.setFechaNacimiento(dateFormat.format(this.adoptanteView.txtFechaNac.getDate()));
        
        adoptante.setTelefono(this.adoptanteView.txtTelefono.getText());
        adoptante.setDireccion(this.adoptanteView.txtDireccion.getText());
        adoptante.setCorreo(this.adoptanteView.txtCorreo.getText());
        adoptante.setPropietario(this.adoptanteView.cbxPropietario.getSelectedIndex());
        adoptante.setPermiso(this.adoptanteView.cbxPermiso.getSelectedIndex());
        
        this.adoptanteDAO.update(adoptante);
        listarAdoptantes();
    }

    private void eliminarAdoptante(String id) {
        this.adoptanteDAO.delete(Integer.parseInt(id));
        listarAdoptantes();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource().equals(this.adoptanteView.tblAdoptantes)) {
            final int codigo = 0;
            int idx = this.adoptanteView.tblAdoptantes.getSelectedRow();
            if(idx >= 0) {
                buscarAdoptante(this.adoptanteView.tblAdoptantes.getValueAt(idx, codigo).toString());
            }
        }
    }
}
