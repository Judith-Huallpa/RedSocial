package com.emergentes.modelo;
import java.util.Date;

public class Notificaciones {
    private int notification_id;
    private Usuario user_id;
    private String tipo_de_notificacion;
    private String contenido_de_notificacion;
    private Date fechaNotificacion;

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public Usuario getUser_id() {
        return user_id;
    }

    public void setUser_id(Usuario user_id) {
        this.user_id = user_id;
    }

    public String getTipo_de_notificacion() {
        return tipo_de_notificacion;
    }

    public void setTipo_de_notificacion(String tipo_de_notificacion) {
        this.tipo_de_notificacion = tipo_de_notificacion;
    }

    public String getContenido_de_notificacion() {
        return contenido_de_notificacion;
    }

    public void setContenido_de_notificacion(String contenido_de_notificacion) {
        this.contenido_de_notificacion = contenido_de_notificacion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    @Override
    public String toString() {
        return "Notificaciones{" + "notification_id=" + notification_id + ", user_id=" + user_id + ", tipo_de_notificacion=" + tipo_de_notificacion + ", contenido_de_notificacion=" + contenido_de_notificacion + ", fechaNotificacion=" + fechaNotificacion + '}';
    }
    
}