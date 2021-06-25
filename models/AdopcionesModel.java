
package models;

public class AdopcionesModel {
    
    private int idMascota;
    private int idAdoptante;
    private String fechaAdop;

    public AdopcionesModel() {
    }

    public AdopcionesModel(int idMascota, int idAdoptante, String fechaAdop) {
        this.idMascota = idMascota;
        this.idAdoptante = idAdoptante;
        this.fechaAdop = fechaAdop;
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
