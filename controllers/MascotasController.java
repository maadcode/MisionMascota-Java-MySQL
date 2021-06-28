
package controllers;

import dao.MascotaDAO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import models.MascotaModel;
import views.Menu;
import views.ViewMascotas;

public class MascotasController implements ActionListener {
    private MascotaDAO mascotaDAO;
    private ViewMascotas mascotaView;
    private Menu menu;
    String imagePath = null;

    public MascotasController(MascotaDAO mascotaDAO, ViewMascotas mascotaView, Menu menu) {
        this.mascotaDAO = mascotaDAO;
        this.mascotaView = mascotaView;
        this.menu = menu;
        
        this.mascotaView.setSize(750, 520);
        this.menu.contentPanel.removeAll();
        this.menu.contentPanel.add(this.mascotaView);
        this.menu.contentPanel.repaint();
        this.menu.lblTitle.setText("Mascotas");
        
        this.mascotaView.btnBuscarMascota.addActionListener(this);
        this.mascotaView.btnAgregarMascota.addActionListener(this);
        this.mascotaView.btnEditarMascota.addActionListener(this);
        this.mascotaView.btnEliminarMascota.addActionListener(this);
        this.mascotaView.btnAgregarImagen.addActionListener(this);
        listarMascotas();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(mascotaView.btnBuscarMascota)) {
            buscarMascota(mascotaView.txtCodigoMascota.getText());
        }
        if(e.getSource().equals(mascotaView.btnAgregarMascota)) {
            agregarMascota();
        }
        if(e.getSource().equals(mascotaView.btnEditarMascota)) {
            editarMascota(mascotaView.txtCodigoMascota.getText());
        }
        if(e.getSource().equals(mascotaView.btnEliminarMascota)) {
            eliminarMascota(mascotaView.txtCodigoMascota.getText());
        }
        if(e.getSource().equals(mascotaView.btnAgregarImagen)) {
            agregarImagen();
        }
    }
    
    public void listarMascotas() {
        ArrayList<MascotaModel> list;
        list = this.mascotaDAO.getListaMascotas();
        DefaultTableModel table = (DefaultTableModel) mascotaView.tblMascotas.getModel();
        // Clean table
        table.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdMascota();
            row[1] = list.get(i).getNombre();
            row[2] = list.get(i).getRaza();
            row[3] = list.get(i).getEstadoMascota();
            
            table.addRow(row);
        }
    }
    
    private void agregarMascota() {
        this.mascotaDAO.mascotaModel.setNombre(this.mascotaView.txtNombreMascota.getText());
        this.mascotaDAO.mascotaModel.setPeso(Float.parseFloat(this.mascotaView.txtPeso.getText()));
        this.mascotaDAO.mascotaModel.setRaza(this.mascotaView.txtRaza.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        this.mascotaDAO.mascotaModel.setFechaNacimiento(dateFormat.format(mascotaView.txtFechaNac.getDate()));
        
        this.mascotaDAO.mascotaModel.setEstadoMascota(this.mascotaView.cbxEstadoMascota.getSelectedIndex()+1);
        this.mascotaDAO.mascotaModel.setImageURL(imagePath);
        
        this.mascotaDAO.create();
        listarMascotas();
    }

    private void buscarMascota(String id) {
        MascotaModel mascota = this.mascotaDAO.getMascota(id);
        this.mascotaView.txtNombreMascota.setText(mascota.getNombre());
        this.mascotaView.txtPeso.setText(mascota.getPeso()+"");
        this.mascotaView.txtRaza.setText(mascota.getRaza());
        
        Date fecha;
        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(mascota.getFechaNacimiento());
            this.mascotaView.txtFechaNac.setDate(fecha);        
        } catch (ParseException ex) {
            Logger.getLogger(MascotasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.mascotaView.cbxEstadoMascota.setSelectedIndex(mascota.getEstadoMascota()+1);
        
        if(mascota.getImageURL() != null) {
            this.mascotaView.lblImage.setIcon(resizeImage(mascota.getImageURL()));
        }
    }

    private void editarMascota(String id) {
        this.mascotaDAO.mascotaModel.setNombre(this.mascotaView.txtNombreMascota.getText());
        this.mascotaDAO.mascotaModel.setPeso(Float.parseFloat(this.mascotaView.txtPeso.getText()));
        this.mascotaDAO.mascotaModel.setRaza(this.mascotaView.txtRaza.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        this.mascotaDAO.mascotaModel.setFechaNacimiento(dateFormat.format(this.mascotaView.txtFechaNac.getDate()));
        
        this.mascotaDAO.mascotaModel.setEstadoMascota(this.mascotaView.cbxEstadoMascota.getSelectedIndex()+1);
        
        if(imagePath != null) {
            this.mascotaDAO.mascotaModel.setImageURL(imagePath);
        }
        
        this.mascotaDAO.update(id);
        listarMascotas();
    }

    private void eliminarMascota(String id) {
        this.mascotaDAO.delete(id);
        listarMascotas();
    }
    
    public ImageIcon resizeImage(String imagePath) {
        ImageIcon myImage = null;
        if(imagePath != null) {
            myImage = new ImageIcon(imagePath);
        }
        
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(this.mascotaView.lblImage.getWidth(), this.mascotaView.lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }
    
    public void agregarImagen() {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            this.mascotaView.lblImage.setIcon(resizeImage(path));
            imagePath = path;
        } else {
            System.out.println("Archivo no seleccionado");
        }
    }
}
