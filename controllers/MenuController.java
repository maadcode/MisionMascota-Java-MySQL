
package controllers;

import dao.AdopcionesDAO;
import dao.AdoptanteDAO;
import dao.MascotaDAO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import models.AdopcionesModel;
import models.AdoptanteModel;
import models.MascotaModel;
import views.Dashboard;
import views.Menu;
import views.ViewAdopciones;
import views.ViewAdoptantes;
import views.ViewMascotas;

public class MenuController implements MouseListener {
    private Menu view;

    public MenuController() {
        this.view = new Menu();
        
        view.btnAdoptantes.addMouseListener((MouseListener) this);
        view.btnAdopciones.addMouseListener((MouseListener) this);
        view.btnCatalogo.addMouseListener((MouseListener) this);
        view.btnDashboard.addMouseListener((MouseListener) this);
        view.btnMascotas.addMouseListener((MouseListener) this);
        
        Dashboard dashboard = new Dashboard();
        AdopcionesModel adopcionModel = new AdopcionesModel();
        AdopcionesDAO adopcionDAO = new AdopcionesDAO(adopcionModel);
        MascotaModel mascotaModel = new MascotaModel();
        MascotaDAO mascotaDAO = new MascotaDAO(mascotaModel);
        DashboardController dashboardController = new DashboardController(adopcionDAO, mascotaDAO, dashboard, this.view);
        
        this.view.setVisible(true);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource().equals(this.view.btnAdoptantes)) {
            ViewAdoptantes adoptanteView = new ViewAdoptantes();
            AdoptanteModel adoptanteModel = new AdoptanteModel();
            AdoptanteDAO adoptanteDAO = new AdoptanteDAO(adoptanteModel);
            AdoptantesController adoptanteController = new AdoptantesController(adoptanteDAO, adoptanteView, this.view);
        }
        if(e.getSource().equals(this.view.btnAdopciones)) {
            ViewAdopciones adopcionesView = new ViewAdopciones();
            AdopcionesModel adopcionModel = new AdopcionesModel();
            AdopcionesDAO adopcionDAO = new AdopcionesDAO(adopcionModel);
            AdopcionesController adopcionController = new AdopcionesController(adopcionDAO, adopcionesView, this.view);
        }
        if(e.getSource().equals(this.view.btnCatalogo)) {
            
        }
        if(e.getSource().equals(this.view.btnDashboard)) {
            Dashboard dashboard = new Dashboard();
            AdopcionesModel adopcionModel = new AdopcionesModel();
            AdopcionesDAO adopcionDAO = new AdopcionesDAO(adopcionModel);
            MascotaModel mascotaModel = new MascotaModel();
            MascotaDAO mascotaDAO = new MascotaDAO(mascotaModel);
            DashboardController dashboardController = new DashboardController(adopcionDAO, mascotaDAO, dashboard, this.view);
        }
        if(e.getSource().equals(this.view.btnMascotas)) {
            ViewMascotas mascotaView = new ViewMascotas();
            MascotaModel mascotaModel = new MascotaModel();
            MascotaDAO mascotaDAO = new MascotaDAO(mascotaModel);
            MascotasController mascotaController = new MascotasController(mascotaDAO, mascotaView, this.view);
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
