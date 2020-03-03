/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.modelo;

/**
 *
 * @author Rosa
 */
public class JuegoVO {

    private int id_juego;
    private String nombre_juego;
    private String sistema_operativo;
    private String tipo;
    private String descripcion;
    private int pegi;
    private double precio;

    public JuegoVO(int id_juego, String nombre_juego, String sistema_operativo, String tipo, String descripcion, int pegi, double precio) {
        this.id_juego = id_juego;
        this.nombre_juego = nombre_juego;
        this.sistema_operativo = sistema_operativo;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.pegi = pegi;
        this.precio = precio;
    }

    public JuegoVO() {
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public String getNombre_juego() {
        return nombre_juego;
    }

    public void setNombre_juego(String nombre_juego) {
        this.nombre_juego = nombre_juego;
    }

    public String getSistema_operativo() {
        return sistema_operativo;
    }

    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "JuegoVO{" + "id_juego=" + id_juego + ", nombre_juego=" + nombre_juego + ", sistema_operativo=" + sistema_operativo + ", tipo=" + tipo + ", descripcion=" + descripcion + ", pegi=" + pegi + ", precio=" + precio + '}';
    }
}
