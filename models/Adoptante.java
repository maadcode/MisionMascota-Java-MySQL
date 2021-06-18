
package models;

public class Adoptante {
    private String id_adoptante;
    private String nombres;
    private String apellidos;
    private String dni;
    private String direccion;
    private int edad;
    private String numero;
    private boolean propietarioCasa;
    private boolean permisoDueno;

    public Adoptante() {
    }

    public Adoptante(String id_adoptante, String nombres, String apellidos, String dni, String direccion, int edad, String numero, boolean propietarioCasa, boolean permisoDueno) {
        this.id_adoptante = id_adoptante;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.edad = edad;
        this.numero = numero;
        this.propietarioCasa = propietarioCasa;
        this.permisoDueno = permisoDueno;
    }

    public String getId_adoptante() {
        return id_adoptante;
    }

    public void setId_adoptante(String id_adoptante) {
        this.id_adoptante = id_adoptante;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isPropietarioCasa() {
        return propietarioCasa;
    }

    public void setPropietarioCasa(boolean propietarioCasa) {
        this.propietarioCasa = propietarioCasa;
    }

    public boolean isPermisoDueno() {
        return permisoDueno;
    }

    public void setPermisoDueno(boolean permisoDueno) {
        this.permisoDueno = permisoDueno;
    }
    
    public boolean EsApto() {
        return this.isPropietarioCasa() || this.isPermisoDueno();
    }
    
    public String mostrarInfo() {
        String info = "Nombre: " + this.getNombres() + " " + this.getApellidos() + 
                        "\nEdad: " + this.getEdad() + 
                        "\nDirección: " + this.getDireccion()+ 
                        "\nDNI: " + this.getDni();
        return info;
    }
}
