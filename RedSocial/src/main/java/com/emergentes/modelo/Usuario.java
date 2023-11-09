package com.emergentes.modelo;
import java.util.Date;

public class Usuario {
    private int user_id;
    private String nombre;
    private String correo_electronico; 
    private String contrasena ;
    private Date fecha_registro;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    @Override
    public String toString() {
        return "Usuario{" + "user_id=" + user_id + ", nombre=" + nombre + ", correo_electronico=" + correo_electronico + ", contrasena=" + contrasena + ", fecha_registro=" + fecha_registro + '}';
    }
    
}