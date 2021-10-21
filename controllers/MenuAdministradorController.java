
package controllers;

import dao.AdopcionDAO;
import dao.AdoptanteDAO;
import dao.MascotaDAO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import views.Dashboard;
import views.MenuAdministrador;
import views.ViewAdoptantes;
import views.ViewReportes;

public class MenuAdministradorController implements MouseListener {
    private MenuAdministrador view;

    public MenuAdministradorController() {
        this.view = new MenuAdministrador();
        
        this.view.btnAsistentes.addMouseListener(this);
        this.view.btnReportes.addMouseListener(this);
        
        Dashboard dashboard = new Dashboard();
        AdopcionDAO adopcionDAO = new AdopcionDAO();
        MascotaDAO mascotaDAO = new MascotaDAO();
        ReportesController reportesController = new ReportesController(adopcionDAO, mascotaDAO, dashboard, this.view);
        
        this.view.setVisible(true);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource().equals(this.view.btnAsistentes)) {
            ViewAdoptantes adoptanteView = new ViewAdoptantes();
            AdoptanteDAO adoptanteDAO = new AdoptanteDAO();
            AdoptantesController adoptanteController = new AdoptantesController(adoptanteDAO, adoptanteView, this.view);
        }
        if(e.getSource().equals(this.view.btnReportes)) {
            ViewReportes viewReportes = new ViewReportes();
            AdopcionDAO reportesDAO = new AdopcionDAO();
            MascotaDAO mascotaDAO = new MascotaDAO();
            DashboardController dashboardController = new DashboardController(adopcionDAO, mascotaDAO, dashboard, this.view);
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
