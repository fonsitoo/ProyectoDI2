package edu.arf.liceo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Item {
    private int idItem;
    private String nombre;
    private String descripcion;
    private double precio;
    private double floatValue;
    private LocalDate fechaPublicacion;
    private int idUsuario;
    private int idJuego;

    private List<Categoria> categorias;

    public Item(int idItem, String nombre, String descripcion, double precio, double floatValue,
                LocalDate fechaPublicacion, int idUsuario, int idJuego) {
        this.idItem = idItem;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.floatValue = floatValue;
        this.fechaPublicacion = fechaPublicacion;
        this.idUsuario = idUsuario;
        this.idJuego = idJuego;
        this.categorias = new ArrayList<>();
    }

    public Item() {
        this.categorias = new ArrayList<>();
    }

    public int getIdItem() { return idItem; }
    public void setIdItem(int idItem) { this.idItem = idItem; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public double getFloatValue() { return floatValue; }
    public void setFloatValue(double floatValue) { this.floatValue = floatValue; }
    public LocalDate getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDate fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public int getIdJuego() { return idJuego; }
    public void setIdJuego(int idJuego) { this.idJuego = idJuego; }
    public List<Categoria> getCategorias() { return categorias; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }

    public void addCategoria(Categoria cat) {
        if (!this.categorias.contains(cat)) {
            this.categorias.add(cat);
        }
    }

    @Override
    public String toString() {
        return nombre + " (" + precio + "€)";
    }
}