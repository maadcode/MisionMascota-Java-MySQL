
package models;

public class Mascota {
    private String id_mascota;
    private String nombre;
    private String peso;
    private String raza;
    private String fecha_nacimiento;
    private String fecha_ingreso;

    public Mascota() {
    }

    public Mascota(String id_mascota, String nombre, String peso, String raza, String fecha_nacimiento, String fecha_ingreso) {
        this.id_mascota = id_mascota;
        this.nombre = nombre;
        this.peso = peso;
        this.raza = raza;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(String id_mascota) {
        this.id_mascota = id_mascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    
    public void registrarMascota() {}
    
    public String buscarMascota() {
        return "";
    }
    
    public void actualizarMascota() {}

    public void eliminarMascota() {}

} 
