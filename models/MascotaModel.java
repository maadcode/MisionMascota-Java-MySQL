
package models;

public class MascotaModel {
    private String idMascota;
    private String nombre;
    private String peso;
    private String raza;
    private String fechaNacimiento;
    private String fechaIngreso;

    public MascotaModel() {
    }
    
    public MascotaModel(String idMascota, String nombre, String peso, String raza, String fechaNacimiento, String fechaIngreso) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.peso = peso;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
} 
