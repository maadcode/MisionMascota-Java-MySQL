
package models;

import dao.AdoptanteDAO;

public class AdoptanteModel {
    private String nombre;
    private String apellido;
    private String DNI;
    private int edad;
    private String telefono;
    private String direccion;
    private String propietario;
    private String permiso;

    public AdoptanteModel() {
    }

    public AdoptanteModel(String nombre, String apellido, String DNI, int edad, String telefono, String direccion, String propietario, String permiso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.edad = edad;
        this.telefono = telefono;
        this.direccion = direccion;
        this.propietario = propietario;
        this.permiso = permiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }
    
    
}
