/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.modelo;

import java.time.LocalDate;

/**
 *
 * @author Jose
 */
public class UsuarioVO {

    private int id_usuario;
    private String nombre;
    private LocalDate fecha_nacimiento;
    private String nickname;

    // Constructor por defecto
    public UsuarioVO() {
    }

    // Constructor parametrizado
    public UsuarioVO(int id_usuario, String nombre, LocalDate fecha_nacimiento, String nickname) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nickname = nickname;
    }

    // Getters
    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getNickname() {
        return nickname;
    }

    // Setters
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // ToString
    @Override
    public String toString() {
        return "UsuarioVO{" + "id_usuario=" + id_usuario + ", nombre=" + nombre + ", fecha_nacimiento=" + fecha_nacimiento + ", nickname=" + nickname + '}';
    }

}
