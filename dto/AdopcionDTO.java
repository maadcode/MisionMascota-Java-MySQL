
package dto;

public class AdopcionDTO {
    
    private int idAdopcion;
    private int idMascota;
    private String nombreMascota;
    private int idAdoptante;
    private String nombreAdoptante;
    private String fechaAdop;

    public AdopcionDTO() {
    }

    public AdopcionDTO(int idAdopcion, String nombreMascota, String nombreAdoptante, String fechaAdop) {
        this.idAdopcion = idAdopcion;
        this.nombreMascota = nombreMascota;
        this.nombreAdoptante = nombreAdoptante;
        this.fechaAdop = fechaAdop;
    }

    public AdopcionDTO(int idAdopcion, int idMascota, int idAdoptante, String fechaAdop) {
        this.idAdopcion = idAdopcion;
        this.idMascota = idMascota;
        this.idAdoptante = idAdoptante;
        this.fechaAdop = fechaAdop;
    }
    
    public int getIdAdopcion() {
        return idAdopcion;
    }

    public void setIdAdopcion(int idAdopcion) {
        this.idAdopcion = idAdopcion;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdAdoptante() {
        return idAdoptante;
    }

    public void setIdAdoptante(int idAdoptante) {
        this.idAdoptante = idAdoptante;
    }

    public String getFechaAdop() {
        return fechaAdop;
    }

    public void setFechaAdop(String fechaAdop) {
        this.fechaAdop = fechaAdop;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getNombreAdoptante() {
        return nombreAdoptante;
    }

    public void setNombreAdoptante(String nombreAdoptante) {
        this.nombreAdoptante = nombreAdoptante;
    }
}
