
package controllers;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import dao.AdopcionDAO;
import dao.AdoptanteDAO;
import dao.MascotaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dto.AdopcionDTO;
import dto.AdoptanteDTO;
import dto.MascotaDTO;
import services.PDFFile;
import views.MenuAsistente;
import views.ViewAdopciones;

public class AdopcionesController implements ActionListener, KeyListener {
    private AdopcionDAO adopcionesDAO;
    private ViewAdopciones adopcionesView;
    private MenuAsistente menu;

    public AdopcionesController(AdopcionDAO adopcionesDAO, ViewAdopciones adopcionesView, MenuAsistente menu) {
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
        this.adopcionesView.btnImprimirAdopcion.addActionListener(this);
        this.adopcionesView.tblAdopciones.addKeyListener(this);
        listarAdopciones();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(adopcionesView.btnBuscarAdoptante)) {
            if(this.adopcionesView.txtCodigoAdoptante.getText().length() > 0) {
                mostrarAdoptante(adopcionesView.txtCodigoAdoptante.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Coloca el código del adoptante a buscar");
            }
        }
        if(e.getSource().equals(adopcionesView.btnBuscarMascota)) {
            if(this.adopcionesView.txtCodigoMascota.getText().length() > 0) {
                mostrarMascota(adopcionesView.txtCodigoMascota.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Coloca el código de la mascota a buscar");
            }
        }
        if(e.getSource().equals(adopcionesView.btnBuscarAdopcion)) {
            if(this.adopcionesView.txtCodigoAdopcion.getText().length() > 0) {
                buscarAdopcion(adopcionesView.txtCodigoAdopcion.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Coloca el código de la adopción a buscar");
            }
        }
        if(e.getSource().equals(adopcionesView.btnAgregarAdopcion)) {
            agregarAdopcion();
        }
        if(e.getSource().equals(adopcionesView.btnEditarAdopcion)) {
            if(this.adopcionesView.txtCodigoAdopcion.getText().length() > 0) {
                editarAdopcion(adopcionesView.txtCodigoAdopcion.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Coloca el código de la adopción a editar");
            }
        }
        if(e.getSource().equals(adopcionesView.btnEliminarAdopcion)) {
            if(this.adopcionesView.txtCodigoAdopcion.getText().length() > 0) {
                eliminarMascota(adopcionesView.txtCodigoAdopcion.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Coloca el código de la adopción a eliminar");
            }
        }
        if(e.getSource().equals(adopcionesView.btnImprimirAdopcion)) {
            if(this.adopcionesView.txtCodigoAdopcion.getText().length() > 0) {
                imprimirAdopcion(adopcionesView.txtCodigoAdopcion.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Coloca el código de la adopción a imprimir");
            }
        }
    }
    
    private void agregarAdopcion() {
        AdopcionDTO adopcion = new AdopcionDTO();
        adopcion.setIdAdoptante(Integer.parseInt(this.adopcionesView.txtCodigoAdoptante.getText()));
        adopcion.setIdMascota(Integer.parseInt(this.adopcionesView.txtCodigoMascota.getText()));
        
        this.adopcionesDAO.create(adopcion);
        MascotaDAO mascotaDAO = new MascotaDAO();
        mascotaDAO.cambiarEstado(Integer.parseInt(this.adopcionesView.txtCodigoMascota.getText()), 3);
        listarAdopciones();
    }
    
    private void buscarAdopcion(String id) {
        AdopcionDTO adopcion = this.adopcionesDAO.getAdopcion(Integer.parseInt(id));
        if(adopcion != null) {
            this.adopcionesView.txtCodigoAdopcion.setText(adopcion.getIdAdopcion()+"");
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
        AdopcionDTO adopcion = new AdopcionDTO();
        adopcion.setIdAdopcion(Integer.parseInt(id));
        adopcion.setIdAdoptante(Integer.parseInt(this.adopcionesView.txtCodigoAdoptante.getText()));
        adopcion.setIdMascota(Integer.parseInt(this.adopcionesView.txtCodigoMascota.getText()));
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        adopcion.setFechaAdop(dateFormat.format(this.adopcionesView.txtFechaAdopcion.getDate()));        
        
        this.adopcionesDAO.update(adopcion);
        listarAdopciones();
    }
    
    private void eliminarMascota(String id) {
        this.adopcionesDAO.delete(Integer.parseInt(id));
        listarAdopciones();
    }
    
    public void listarAdopciones() {
        ArrayList<AdopcionDTO> list = this.adopcionesDAO.read(); 
        DefaultTableModel table = (DefaultTableModel) adopcionesView.tblAdopciones.getModel();
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

    public void mostrarAdoptante(String id) {
        String result;
        AdoptanteDAO adoptanteDAO = new AdoptanteDAO();
        AdoptanteDTO adoptante = adoptanteDAO.getAdoptante(Integer.parseInt(id));
        
        result = "Nombre : " + adoptante.getNombre() +
                 "\nApellido : " + adoptante.getApellido()+
                 "\nDNI : " + adoptante.getDNI()+
                 "\nFecha Nac. : " + adoptante.getFechaNacimiento()+
                 "\nDirección : " + adoptante.getDireccion();
        this.adopcionesView.txtAdoptanteResult.setText(result);
    }

    public void mostrarMascota(String id) {
        String result;
        MascotaDAO mascotaDAO = new MascotaDAO();
        MascotaDTO mascota = mascotaDAO.getMascota(Integer.parseInt(id));
        
        result = "Nombre : " + mascota.getNombre() +
                 "\nRaza : " + mascota.getRaza() +
                 "\nFecha de Ingreso : " + mascota.getFechaIngreso();
        this.adopcionesView.txtMascotaResult.setText(result);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource().equals(this.adopcionesView.tblAdopciones)) {
            final int codigo = 0;
            int idx = this.adopcionesView.tblAdopciones.getSelectedRow();
            if(idx >= 0) {
                buscarAdopcion(this.adopcionesView.tblAdopciones.getValueAt(idx, codigo).toString());
            }
        }
    }

    private void imprimirAdopcion(String id) {
        AdopcionDTO adopcion = this.adopcionesDAO.getAdopcion(Integer.parseInt(id));
        
        AdoptanteDAO adoptanteDAO = new AdoptanteDAO();
        AdoptanteDTO adoptante = adoptanteDAO.getAdoptante(adopcion.getIdAdoptante());
        
        MascotaDAO mascotaDAO = new MascotaDAO();
        MascotaDTO mascota = mascotaDAO.getMascota(adopcion.getIdMascota());
        
        String dataAdopcion = "\nDatos de adopción*******************************" +
                                "\nCódigo de adopción : " + adopcion.getIdAdopcion()+
                                "\nFecha de adopción : " + adopcion.getFechaAdop();
        
        String dataAdoptante = "\nDatos del adoptante*******************************" +
                                "\nNombre completo : " + adoptante.getNombre() + " " + adoptante.getApellido() +
                                "\nDNI : " + adoptante.getDNI() +
                                "\nDirección : " + adoptante.getDireccion()+
                                "\nTelefono : " + adoptante.getTelefono()+
                                "\nCorreo : " + adoptante.getCorreo();
        
        String dataMascota = "\nDatos de la mascota*******************************" +
                                "\nNombre : " + mascota.getNombre() +
                                "\nRaza : " + mascota.getRaza()+
                                "\nFecha de nacimiento : " + mascota.getFechaNacimiento() + "\n";
        
        PDFFile pdf = new PDFFile();
        Image header;
        Image title;
        Image footer;
        try {
            header = Image.getInstance("src/assets/certificado/header.png");
            title = Image.getInstance("src/assets/certificado/titulo.png");
            footer = Image.getInstance("src/assets/certificado/firmas.png");
            pdf.setHeader(header);
            pdf.setTitle(title);
            pdf.setContent(new Paragraph(dataAdopcion + dataAdoptante + dataMascota));
            pdf.setFooter(footer);
            pdf.generate("Adopcion"+adopcion.getIdAdopcion());
        } catch (BadElementException ex) {
            Logger.getLogger(AdopcionesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdopcionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
