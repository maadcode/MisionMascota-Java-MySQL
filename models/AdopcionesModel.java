
package models;

public class AdopcionesModel {
    
    private int idAdopcion;
    private int idMascota;
    private int idAdoptante;
    private String fechaAdop;

    public AdopcionesModel() {
    }

    public AdopcionesModel(int idAdopcion, int idMascota, int idAdoptante, String fechaAdop) {
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
    
}
