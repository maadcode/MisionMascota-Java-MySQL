
package controllers;

import dao.MascotaDAO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import dto.MascotaDTO;
import views.MenuAsistente;
import views.ViewMascotas;

public class MascotasController implements ActionListener, KeyListener {
    private MascotaDAO mascotaDAO;
    private ViewMascotas mascotaView;
    private MenuAsistente menu;
    String imagePath = null;

    public MascotasController(MascotaDAO mascotaDAO, ViewMascotas mascotaView, MenuAsistente menu) {
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
        this.mascotaView.tblMascotas.addKeyListener(this);
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
        ArrayList<MascotaDTO> list = this.mascotaDAO.read();
        DefaultTableModel table = (DefaultTableModel) mascotaView.tblMascotas.getModel();
        // Clean table
        table.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdMascota();
            row[1] = list.get(i).getNombre();
            row[2] = list.get(i).getRaza();
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
    
    private void agregarMascota() {
        MascotaDTO mascota = new MascotaDTO();
        mascota.setNombre(this.mascotaView.txtNombreMascota.getText());
        mascota.setPeso(Float.parseFloat(this.mascotaView.txtPeso.getText()));
        mascota.setRaza(this.mascotaView.txtRaza.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mascota.setFechaNacimiento(dateFormat.format(mascotaView.txtFechaNac.getDate()));
        
        mascota.setEstadoMascota(this.mascotaView.cbxEstadoMascota.getSelectedIndex()+1);
        mascota.setImageURL(imagePath);
        
        this.mascotaDAO.create(mascota);
        listarMascotas();
    }

    private void buscarMascota(String id) {
        MascotaDTO mascota = this.mascotaDAO.getMascota(Integer.parseInt(id));
        if(mascota != null) {
            this.mascotaView.txtCodigoMascota.setText(mascota.getIdMascota()+"");
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

            this.mascotaView.cbxEstadoMascota.setSelectedIndex(mascota.getEstadoMascota()-1);

            if(mascota.getImageURL() != null) {
                this.mascotaView.lblImage.setIcon(resizeImage(mascota.getImageURL()));
                imagePath = mascota.getImageURL();
            } else {
                this.mascotaView.lblImage.setIcon(resizeImage("src/assets/no-image.png"));
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe la mascota con el id ingresado");
        }
    }

    private void editarMascota(String id) {
        MascotaDTO mascota = new MascotaDTO();
        mascota.setIdMascota(Integer.parseInt(id));
        mascota.setNombre(this.mascotaView.txtNombreMascota.getText());
        mascota.setPeso(Float.parseFloat(this.mascotaView.txtPeso.getText()));
        mascota.setRaza(this.mascotaView.txtRaza.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mascota.setFechaNacimiento(dateFormat.format(this.mascotaView.txtFechaNac.getDate()));
        
        mascota.setEstadoMascota(this.mascotaView.cbxEstadoMascota.getSelectedIndex()+1);
        
        if(imagePath != null) {
            mascota.setImageURL(imagePath);
        }
        
        this.mascotaDAO.update(mascota);
        listarMascotas();
    }

    private void eliminarMascota(String id) {
        this.mascotaDAO.delete(Integer.parseInt(id));
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource().equals(this.mascotaView.tblMascotas)) {
            final int codigo = 0;
            int idx = this.mascotaView.tblMascotas.getSelectedRow();
            if(idx >= 0) {
                buscarMascota(this.mascotaView.tblMascotas.getValueAt(idx, codigo).toString());
            }
        }
    }
}
