
package models;

public class MascotaModel {
    private String nombre;
    private float peso;
    private String raza;
    private String fechaNacimiento;
    private String fechaIngreso;
    private int estadoMascota;

    public MascotaModel() {
    }
    
    public MascotaModel(String nombre, float peso, String raza, String fechaNacimiento, String fechaIngreso, int estadoMascota) {
        this.nombre = nombre;
        this.peso = peso;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.estadoMascota = estadoMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
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
    
    public int getEstadoMascota() {
        return estadoMascota;
    }

    public void setEstadoMascota(int estadoMascota) {
        this.estadoMascota = estadoMascota;
    }
} 
