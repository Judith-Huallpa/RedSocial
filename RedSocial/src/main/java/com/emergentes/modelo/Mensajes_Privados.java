package com.emergentes.modelo;
import java.util.Date;

public class Mensajes_Privados {
    private int message_id;
    private Usuario user_id_from;
    private Usuario user_id_to;
    private String contenido_del_mensaje;
    private Date fecha_envio;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public Usuario getUser_id_from() {
        return user_id_from;
    }

    public void setUser_id_from(Usuario user_id_from) {
        this.user_id_from = user_id_from;
    }

    public Usuario getUser_id_to() {
        return user_id_to;
    }

    public void setUser_id_to(Usuario user_id_to) {
        this.user_id_to = user_id_to;
    }

    public String getContenido_del_mensaje() {
        return contenido_del_mensaje;
    }

    public void setContenido_del_mensaje(String contenido_del_mensaje) {
        this.contenido_del_mensaje = contenido_del_mensaje;
    }

    public Date getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(Date fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    @Override
    public String toString() {
        return "Mensajes_Privados{" + "message_id=" + message_id + ", user_id_from=" + user_id_from + ", user_id_to=" + user_id_to + ", contenido_del_mensaje=" + contenido_del_mensaje + ", fecha_envio=" + fecha_envio + '}';
    }
        
}