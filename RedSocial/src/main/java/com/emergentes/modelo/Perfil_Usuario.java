package com.emergentes.modelo;
public class Perfil_Usuario {
    private int profile_Id;
    private Usuario user_id;
    private String foto_de_perfil;
    private String descripcion;

    public int getProfile_Id() {
        return profile_Id;
    }

    public void setProfile_Id(int profile_Id) {
        this.profile_Id = profile_Id;
    }

    public Usuario getUser_id() {
        return user_id;
    }

    public void setUser_id(Usuario user_id) {
        this.user_id = user_id;
    }

    public String getFoto_de_perfil() {
        return foto_de_perfil;
    }

    public void setFoto_de_perfil(String foto_de_perfil) {
        this.foto_de_perfil = foto_de_perfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Perfil_Usuario{" + "profile_Id=" + profile_Id + ", user_id=" + user_id + ", foto_de_perfil=" + foto_de_perfil + ", descripcion=" + descripcion + '}';
    }
       
}