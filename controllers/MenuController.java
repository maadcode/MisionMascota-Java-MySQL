
package controllers;

import dao.AdopcionDAO;
import dao.AdoptanteDAO;
import dao.MascotaDAO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import views.Catalogo;
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
        AdopcionDAO adopcionDAO = new AdopcionDAO();
        MascotaDAO mascotaDAO = new MascotaDAO();
        DashboardController dashboardController = new DashboardController(adopcionDAO, mascotaDAO, dashboard, this.view);
        
        this.view.setVisible(true);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource().equals(this.view.btnAdoptantes)) {
            ViewAdoptantes adoptanteView = new ViewAdoptantes();
            AdoptanteDAO adoptanteDAO = new AdoptanteDAO();
            AdoptantesController adoptanteController = new AdoptantesController(adoptanteDAO, adoptanteView, this.view);
        }
        if(e.getSource().equals(this.view.btnAdopciones)) {
            ViewAdopciones adopcionesView = new ViewAdopciones();
            AdopcionDAO adopcionDAO = new AdopcionDAO();
            AdopcionesController adopcionController = new AdopcionesController(adopcionDAO, adopcionesView, this.view);
        }
        if(e.getSource().equals(this.view.btnCatalogo)) {
            Catalogo catalogo = new Catalogo();
            MascotaDAO mascotaDAO = new MascotaDAO();
            CatalogoController catalogoController = new CatalogoController(mascotaDAO, catalogo, this.view);
        }
        if(e.getSource().equals(this.view.btnDashboard)) {
            Dashboard dashboard = new Dashboard();
            AdopcionDAO adopcionDAO = new AdopcionDAO();
            MascotaDAO mascotaDAO = new MascotaDAO();
            DashboardController dashboardController = new DashboardController(adopcionDAO, mascotaDAO, dashboard, this.view);
        }
        if(e.getSource().equals(this.view.btnMascotas)) {
            ViewMascotas mascotaView = new ViewMascotas();
            MascotaDAO mascotaDAO = new MascotaDAO();
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
