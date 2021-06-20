
package controllers;

import models.UsuarioModel;
import views.Menu;

public class MenuController {
    private UsuarioModel userModel;

    public MenuController() {
        this.userModel = new UsuarioModel();
    }
    
    public void index() {
        new Menu().setVisible(true);
    }
}
