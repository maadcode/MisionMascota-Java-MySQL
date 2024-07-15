
package main;

import controllers.LoginController;
import dao.UsuarioDAO;
import views.Login;

public class App {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Login login = new Login();
        
        LoginController usuarioController = new LoginController(usuarioDAO, login);
    }
}
