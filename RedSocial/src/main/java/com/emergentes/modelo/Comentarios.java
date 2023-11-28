package com.emergentes.modelo;
import java.util.Date;

public class Comentarios {
    private int commentId;
    private Publicaciones post_id;
    private Usuario user_id;
    private String contenido_del_comentario;
    private Date fecha_comentario;

    public Comentarios() {
        this.commentId = 0;
        
        this.contenido_del_comentario = "";
        this.fecha_comentario = new Date();
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Publicaciones getPost_id() {
        return post_id;
    }

    public void setPost_id(Publicaciones post_id) {
        this.post_id = post_id;
    }

    public Usuario getUser_id() {
        return user_id;
    }

    public void setUser_id(Usuario user_id) {
        this.user_id = user_id;
    }

    public String getContenido_del_comentario() {
        return contenido_del_comentario;
    }

    public void setContenido_del_comentario(String contenido_del_comentario) {
        this.contenido_del_comentario = contenido_del_comentario;
    }

    public Date getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(Date fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }

    @Override
    public String toString() {
        return "Comentarios{" + "commentId=" + commentId + ", post_id=" + post_id + ", user_id=" + user_id + ", contenido_del_comentario=" + contenido_del_comentario + ", fecha_comentario=" + fecha_comentario + '}';
    }
    
}
