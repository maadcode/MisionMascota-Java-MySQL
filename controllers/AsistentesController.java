
package controllers;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.MenuAdministrador;
import views.ViewAsistentes;

public class AsistentesController implements ActionListener, KeyListener {
    private UsuarioDAO usuarioDAO;
    private ViewAsistentes asistenteView;
    private MenuAdministrador menu;

    public AsistentesController(UsuarioDAO usuarioDAO, ViewAsistentes asistenteView, MenuAdministrador menu) {
        this.usuarioDAO = usuarioDAO;
        this.asistenteView = asistenteView;
        this.menu = menu;
        
        this.asistenteView.setSize(750, 520);
        this.menu.contentPanel.removeAll();
        this.menu.contentPanel.add(this.asistenteView);
        this.menu.contentPanel.repaint();
        this.menu.lblTitle.setText("Asistentes");
        
        this.asistenteView.btnBuscarUsuario.addActionListener(this);
        this.asistenteView.btnAgregarUsuario.addActionListener(this);
        this.asistenteView.btnEditarUsuario.addActionListener(this);
        this.asistenteView.btnEliminarUsuario.addActionListener(this);
        this.asistenteView.tblUsuarios.addKeyListener(this);
        listarUsuarios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(asistenteView.btnBuscarUsuario)) {
            if(this.asistenteView.txtUsuario.getText() != null) {
                buscarUsuario(this.asistenteView.txtUsuario.getText());
            }
        }
        if(e.getSource().equals(asistenteView.btnAgregarUsuario)) {
            agregarUsuario();
        }
        if(e.getSource().equals(asistenteView.btnEditarUsuario)) {
            if(this.asistenteView.txtUsuario.getText() != null) {
                editarUsuario(this.asistenteView.txtUsuario.getText());
            }
        }
        if(e.getSource().equals(asistenteView.btnEliminarUsuario)) {
            if(this.asistenteView.txtUsuario.getText() != null) {
                eliminarUsuario(this.asistenteView.txtUsuario.getText());
            }
        }
    }
    
    public void listarUsuarios() {
        ArrayList<UsuarioDTO> list = this.usuarioDAO.read();
        DefaultTableModel table = (DefaultTableModel) asistenteView.tblUsuarios.getModel();
        // Clean table
        table.setRowCount(0);
        Object[] row = new Object[4];
        for(int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getUsername();
            row[1] = list.get(i).getNombres();
            row[2] = list.get(i).getApellidos();
            row[3] = list.get(i).getDNI();
            
            table.addRow(row);
        }
    }

    private void agregarUsuario() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsername(this.asistenteView.txtUsuario.getText());
        usuario.setPassword(this.asistenteView.txtClave.getText());
        usuario.setNombres(this.asistenteView.txtNombres.getText());
        usuario.setApellidos(this.asistenteView.txtApellidos.getText());
        usuario.setDNI(this.asistenteView.txtDNI.getText());
        if(this.asistenteView.cbxRol.getSelectedIndex() == 0) {
            usuario.setRol("Asistente");
        } else {
            usuario.setRol("Administrador");
        }

        this.usuarioDAO.create(usuario);
        listarUsuarios();
    }

    private void buscarUsuario(String username) {
        UsuarioDTO usuario = this.usuarioDAO.getUsuario(username);
        if(usuario != null) {
            this.asistenteView.txtUsuario.setText(usuario.getUsername());
            this.asistenteView.txtClave.setText(usuario.getPassword());
            this.asistenteView.txtNombres.setText(usuario.getNombres());
            this.asistenteView.txtApellidos.setText(usuario.getApellidos());
            this.asistenteView.txtDNI.setText(usuario.getDNI());
            this.asistenteView.cbxRol.setSelectedItem(usuario.getRol());
        } else {
            JOptionPane.showMessageDialog(null, "No existe el adoptante con el id ingresado");
        }
    }

    private void editarUsuario(String username) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsername(username);
        usuario.setPassword(this.asistenteView.txtClave.getText());
        usuario.setNombres(this.asistenteView.txtNombres.getText());
        usuario.setApellidos(this.asistenteView.txtApellidos.getText());
        usuario.setDNI(this.asistenteView.txtDNI.getText());
        
        if(this.asistenteView.cbxRol.getSelectedIndex() == 0) {
            usuario.setRol("Asistente");
        } else {
            usuario.setRol("Administrador");
        }

        this.usuarioDAO.update(usuario);
        listarUsuarios();
    }

    private void eliminarUsuario(String username) {
        this.usuarioDAO.delete(username);
        listarUsuarios();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource().equals(this.asistenteView.tblUsuarios)) {
            final int codigo = 0;
            int idx = this.asistenteView.tblUsuarios.getSelectedRow();
            if(idx >= 0) {
                buscarUsuario(this.asistenteView.tblUsuarios.getValueAt(idx, codigo).toString());
            }
        }
    }
}
