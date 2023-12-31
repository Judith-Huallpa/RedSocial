package com.emergentes.modelo;

import java.util.Date;
import java.util.List;

public class Publicaciones {

    private int post_id;
    private Usuario user_id;
    private Grupos grupo_id;
    private String contenido_del_mensaje;
    private String foto_de_publish;
    private Date fecha_publicacion;
    private int CantidadLikes;
    private int cantidadComentario;

    public int getCantidadComentario() {
        return cantidadComentario;
    }

    public void setCantidadComentario(int cantidadComentario) {
        this.cantidadComentario = cantidadComentario;
    }

    private List<Comentarios> comentarios;

    public List<Comentarios> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentarios> comentarios) {
        this.comentarios = comentarios;
    }

    public int getCantidadLikes() {
        return CantidadLikes;
    }

    public void setCantidadLikes(int CantidadLikes) {
        this.CantidadLikes = CantidadLikes;
    }

    public Publicaciones() {
        this.post_id = 0;
        this.user_id = null;
        this.grupo_id = null;
        this.contenido_del_mensaje = "";
        this.foto_de_publish = "";
        this.fecha_publicacion = new Date();
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public Usuario getUser_id() {
        return user_id;
    }

    public void setUser_id(Usuario user_id) {
        this.user_id = user_id;
    }

    public Grupos getGrupo_id() {
        return grupo_id;
    }

    public void setGrupo_id(Grupos grupo_id) {
        this.grupo_id = grupo_id;
    }

    public String getContenido_del_mensaje() {
        return contenido_del_mensaje;
    }

    public void setContenido_del_mensaje(String contenido_del_mensaje) {
        this.contenido_del_mensaje = contenido_del_mensaje;
    }

    public String getFoto_de_publish() {
        return foto_de_publish;
    }

    public void setFoto_de_publish(String foto_de_publish) {
        this.foto_de_publish = foto_de_publish;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    @Override
    public String toString() {
        return "Publicaciones{" + "post_id=" + post_id + ", user_id=" + user_id + ", grupo_id=" + grupo_id + ", contenido_del_mensaje=" + contenido_del_mensaje + ", foto_de_publish=" + foto_de_publish + ", fecha_publicacion=" + fecha_publicacion + '}';
    }
}
