
package controllers;

import dao.UsuarioDAO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import models.UsuarioModel;
import views.Login;

public class UsuariosController implements ActionListener {
    private UsuarioDAO usuarioDAO;
    private Login login;

    public UsuariosController(UsuarioDAO usuarioDAO, Login login) {
        this.usuarioDAO = usuarioDAO;
        this.login = login;
        this.login.setResizable(false);
        this.login.setTitle("Sistema de adopción");
        this.login.setLocationRelativeTo(null);
        
        this.login.btnLogin.addActionListener(this);
        
        paintBackground();
        resetLabels();
        
        this.login.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.login.btnLogin)) {
            resetLabels();
            String user = this.login.txtUsuario.getText();
            String password = this.login.txtPassword.getText();
            if(user.equals("")) {
                this.login.lblErrorUsername.setText("Este campo es requerido.");
            }
            if(password.equals("")) {
                this.login.lblErrorPassword.setText("Este campo es requerido.");
            }
            if(!user.equals("") && !password.equals("")) {
                //this.usuarioDAO.checkLogin(user, password);
                if(true) {
                    this.login.dispose();
                    MenuController menuController = new MenuController();
                } else {
                    this.login.lblErrorPassword.setText("Usuario o contraseña incorrecto.");
                }
            }
        }
    }
    
    public void resetLabels(){
        this.login.lblErrorUsername.setText(null);
        this.login.lblErrorPassword.setText(null);
    }
    
    public void paintBackground() {
        ImageIcon wallpaper = new ImageIcon("src/assets/bg.png");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(this.login.jLabel_wallpaper.getWidth(), this.login.jLabel_wallpaper.getHeight(), Image.SCALE_DEFAULT));
        this.login.jLabel_wallpaper.setIcon(icono);
        this.login.repaint();
    }
}
