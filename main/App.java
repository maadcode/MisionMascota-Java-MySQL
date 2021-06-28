
package main;

import controllers.UsuariosController;
import dao.UsuarioDAO;
import views.Login;

public class App {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Login login = new Login();
        
        UsuariosController usuarioController = new UsuariosController(usuarioDAO, login);
    }
}
