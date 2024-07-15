
package controllers;

import dao.AdopcionDAO;
import dao.MascotaDAO;
import dao.UsuarioDAO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import views.MenuAdministrador;
import views.ViewReportes;
import views.ViewUsuarios;

public class MenuAdministradorController implements MouseListener {
    private MenuAdministrador view;

    public MenuAdministradorController() {
        this.view = new MenuAdministrador();
        
        this.view.btnUsuarios.addMouseListener(this);
        this.view.btnReportes.addMouseListener(this);
        
        ViewReportes reportesView = new ViewReportes();
        AdopcionDAO adopcionDAO = new AdopcionDAO();
        MascotaDAO mascotaDAO = new MascotaDAO();
        ReportesController reportesController = new ReportesController(adopcionDAO, mascotaDAO, reportesView, this.view);
        
        this.view.setVisible(true);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource().equals(this.view.btnUsuarios)) {
            ViewUsuarios usuariosView = new ViewUsuarios();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuariosController usuarioController = new UsuariosController(usuarioDAO, usuariosView, this.view);
        }
        if(e.getSource().equals(this.view.btnReportes)) {
            ViewReportes viewReportes = new ViewReportes();
            AdopcionDAO adopcionDAO = new AdopcionDAO();
            MascotaDAO mascotaDAO = new MascotaDAO();
            ReportesController reportesController = new ReportesController(adopcionDAO, mascotaDAO, viewReportes, this.view);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
