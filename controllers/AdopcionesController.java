
package controllers;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import dao.AdopcionDAO;
import dao.AdoptanteDAO;
import dao.MascotaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import views.Menu;
import views.ViewAdopciones;

public class AdopcionesController implements ActionListener, KeyListener {
    private AdopcionDAO adopcionesDAO;
    private ViewAdopciones adopcionesView;
    private Menu menu;

    public AdopcionesController(AdopcionDAO adopcionesDAO, ViewAdopciones adopcionesView, Menu menu) {
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
        if(e.getSource().equals(adopcionesView.btnImprimirAdopcion)) {
            imprimirAdopcion(adopcionesView.txtCodigoAdopcion.getText());
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
            row[1] = list.get(i).getIdAdoptante();
            row[2] = list.get(i).getIdMascota();
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
                 "\nEdad : " + adoptante.getEdad()+
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
        
        String dataAdopcion = "\nCódigo de adopción : " + adopcion.getIdAdopcion()+
                                "\nFecha de adopción : " + adopcion.getFechaAdop();
        
        String dataAdoptante = "\nNombre completo : " + adoptante.getNombre() + " " + adoptante.getApellido() +
                                "\nDNI : " + adoptante.getDNI() +
                                "\nDirección : " + adoptante.getDireccion()+
                                "\nTelefono : " + adoptante.getTelefono()+
                                "\nCorreo : " + adoptante.getCorreo();
        
        String dataMascota = "\nNombre : " + mascota.getNombre() +
                                "\nRaza : " + mascota.getRaza()+
                                "\nFecha de nacimiento : " + mascota.getFechaNacimiento();
        
        Document documento = new Document();
        String ruta = System.getProperty("user.home");
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "\\Desktop\\Adopcion"+adopcion.getIdAdopcion()+".pdf"));
            Image header = Image.getInstance("src/assets/certificado/header.png");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            Image title = Image.getInstance("src/assets/certificado/titulo.png");
            title.scaleToFit(650, 1000);
            title.setAlignment(Chunk.ALIGN_CENTER);
            
            Image footer = Image.getInstance("src/assets/certificado/firmas.png");
            footer.scaleToFit(650, 1000);
            footer.setAlignment(Chunk.ALIGN_CENTER);
            
            documento.open();
            documento.add(title);
            documento.add(header);
            documento.add(new Paragraph("\nDatos de adopción*******************************"));
            documento.add(new Paragraph(dataAdopcion));
            documento.add(new Paragraph("\nDatos del adoptante*******************************"));
            documento.add(new Paragraph(dataAdoptante));
            documento.add(new Paragraph("\nDatos de la mascota*******************************"));
            documento.add(new Paragraph(dataMascota));
            documento.add(new Paragraph("\n"));
            documento.add(footer);
            documento.close();
            JOptionPane.showMessageDialog(null, "Certificado generado correctamente");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdopcionesController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "catch1");
        } catch (DocumentException ex) {
            Logger.getLogger(AdopcionesController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "catch2");
        } catch (IOException ex) {
            Logger.getLogger(AdopcionesController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "catch3");
        }
    }
}
