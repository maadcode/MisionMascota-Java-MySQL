
package controllers;

import dao.AdoptanteDAO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import models.AdoptanteModel;
import views.Menu;
import views.ViewAdoptantes;

public class MenuController implements MouseListener {
    private Menu view;

    public MenuController() {
        this.view = new Menu();
        
        this.view.setVisible(true);
        view.btnAdoptantes.addMouseListener((MouseListener) this);
        view.btnAdopciones.addMouseListener((MouseListener) this);
        view.btnCatalogo.addMouseListener((MouseListener) this);
        view.btnDashboard.addMouseListener((MouseListener) this);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource().equals(this.view.btnAdoptantes)) {
            System.out.println("clic");
            /*
            ViewAdoptantes nuevoPanel = new ViewAdoptantes();
            nuevoPanel.setSize(750, 520);
            this.view.contentPanel.removeAll();
            this.view.contentPanel.add(nuevoPanel);
            this.view.contentPanel.repaint();
        
            this.view.lblTitle.setText("Adoptantes");
            */
            ViewAdoptantes adoptanteView = new ViewAdoptantes();
            AdoptanteModel adoptanteModel = new AdoptanteModel();
            AdoptanteDAO adoptanteDAO = new AdoptanteDAO(adoptanteModel);
            AdoptantesController adoptanteController = new AdoptantesController(adoptanteDAO, adoptanteView, this.view);
        }
        if(e.getSource().equals(this.view.btnAdopciones)) {
        }
        if(e.getSource().equals(this.view.btnCatalogo)) {
        }
        if(e.getSource().equals(this.view.btnDashboard)) {
        }
        if(e.getSource().equals(this.view.btnMascotas)) {
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
