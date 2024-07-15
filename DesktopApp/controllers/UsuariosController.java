
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
import views.ViewUsuarios;

public class UsuariosController implements ActionListener, KeyListener {
    private UsuarioDAO usuarioDAO;
    private ViewUsuarios usuariosView;
    private MenuAdministrador menu;

    public UsuariosController(UsuarioDAO usuarioDAO, ViewUsuarios usuariosView, MenuAdministrador menu) {
        this.usuarioDAO = usuarioDAO;
        this.usuariosView = usuariosView;
        this.menu = menu;
        
        this.usuariosView.setSize(750, 520);
        this.menu.contentPanel.removeAll();
        this.menu.contentPanel.add(this.usuariosView);
        this.menu.contentPanel.repaint();
        this.menu.lblTitle.setText("Usuarios");
        
        this.usuariosView.btnBuscarUsuario.addActionListener(this);
        this.usuariosView.btnAgregarUsuario.addActionListener(this);
        this.usuariosView.btnEditarUsuario.addActionListener(this);
        this.usuariosView.btnEliminarUsuario.addActionListener(this);
        this.usuariosView.tblUsuarios.addKeyListener(this);
        listarUsuarios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(usuariosView.btnBuscarUsuario)) {
            if(this.usuariosView.txtUsuario.getText() != null) {
                buscarUsuario(this.usuariosView.txtUsuario.getText());
            }
        }
        if(e.getSource().equals(usuariosView.btnAgregarUsuario)) {
            agregarUsuario();
        }
        if(e.getSource().equals(usuariosView.btnEditarUsuario)) {
            if(this.usuariosView.txtUsuario.getText() != null) {
                editarUsuario(this.usuariosView.txtUsuario.getText());
            }
        }
        if(e.getSource().equals(usuariosView.btnEliminarUsuario)) {
            if(this.usuariosView.txtUsuario.getText() != null) {
                eliminarUsuario(this.usuariosView.txtUsuario.getText());
            }
        }
    }
    
    public void listarUsuarios() {
        ArrayList<UsuarioDTO> list = this.usuarioDAO.read();
        DefaultTableModel table = (DefaultTableModel) this.usuariosView.tblUsuarios.getModel();
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
        usuario.setUsername(this.usuariosView.txtUsuario.getText());
        usuario.setPassword(this.usuariosView.txtClave.getText());
        usuario.setNombres(this.usuariosView.txtNombres.getText());
        usuario.setApellidos(this.usuariosView.txtApellidos.getText());
        usuario.setDNI(this.usuariosView.txtDNI.getText());
        usuario.setRol(this.usuariosView.cbxRol.getSelectedIndex() + 1);
        this.usuarioDAO.create(usuario);
        listarUsuarios();
    }

    private void buscarUsuario(String username) {
        UsuarioDTO usuario = this.usuarioDAO.getUsuario(username);
        if(usuario != null) {
            this.usuariosView.txtUsuario.setText(usuario.getUsername());
            this.usuariosView.txtClave.setText(usuario.getPassword());
            this.usuariosView.txtNombres.setText(usuario.getNombres());
            this.usuariosView.txtApellidos.setText(usuario.getApellidos());
            this.usuariosView.txtDNI.setText(usuario.getDNI());
            this.usuariosView.cbxRol.setSelectedIndex(usuario.getRol() - 1);
        } else {
            JOptionPane.showMessageDialog(null, "No existe el usuario con el id ingresado");
        }
    }

    private void editarUsuario(String username) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsername(username);
        usuario.setPassword(this.usuariosView.txtClave.getText());
        usuario.setNombres(this.usuariosView.txtNombres.getText());
        usuario.setApellidos(this.usuariosView.txtApellidos.getText());
        usuario.setDNI(this.usuariosView.txtDNI.getText());
        usuario.setRol(this.usuariosView.cbxRol.getSelectedIndex() + 1);
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
        if(e.getSource().equals(this.usuariosView.tblUsuarios)) {
            final int codigo = 0;
            int idx = this.usuariosView.tblUsuarios.getSelectedRow();
            if(idx >= 0) {
                buscarUsuario(this.usuariosView.tblUsuarios.getValueAt(idx, codigo).toString());
            }
        }
    }
}
