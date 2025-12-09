package edu.arf.liceo.model;

public class Juego {
    private int idJuego;
    private String nombre;
    private String iconoUrl;

    public Juego(int idJuego, String nombre, String iconoUrl) {
        this.idJuego = idJuego;
        this.nombre = nombre;
        this.iconoUrl = iconoUrl;
    }

    public Juego() {}

    public int getIdJuego() { return idJuego; }
    public void setIdJuego(int idJuego) { this.idJuego = idJuego; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIconoUrl() { return iconoUrl; }
    public void setIconoUrl(String iconoUrl) { this.iconoUrl = iconoUrl; }

    @Override
    public String toString() {
        return nombre; // Importante para que el ComboBox muestre el nombre y no el objeto
    }
}