
package dto;

public class MascotaDTO {
    private int idMascota;
    private String nombre;
    private float peso;
    private String raza;
    private String fechaNacimiento;
    private String fechaIngreso;
    private int estadoMascota;
    private String imageURL;

    public MascotaDTO() {
    }
    
    public MascotaDTO(int idMascota, String nombre, float peso, String raza, String fechaNacimiento, String fechaIngreso, int estadoMascota, String imageURL) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.peso = peso;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.estadoMascota = estadoMascota;
        this.imageURL = imageURL;
    }
    
    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
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
    
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
} 
