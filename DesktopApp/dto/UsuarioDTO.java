
package dto;

public class UsuarioDTO {
    private String username;
    private String password;
    private int rol;
    private String nombres;
    private String apellidos;
    private String DNI;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String username, String password, int rol, String nombres, String apellidos, String DNI) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.DNI = DNI;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
