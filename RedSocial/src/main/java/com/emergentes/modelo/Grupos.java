package com.emergentes.modelo;
public class Grupos {
    private int group_id;
    private String nombreDelGrupo;
    private String descripcion;
    private Usuario user_id;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getNombreDelGrupo() {
        return nombreDelGrupo;
    }

    public void setNombreDelGrupo(String nombreDelGrupo) {
        this.nombreDelGrupo = nombreDelGrupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUser_id() {
        return user_id;
    }

    public void setUser_id(Usuario user_id) {
        this.user_id = user_id;
    }
    
    @Override
    public String toString() {
        return "Grupos{" + "group_id=" + group_id + ", nombreDelGrupo=" + nombreDelGrupo + ", descripcion=" + descripcion + ", user_id=" + user_id + '}';
    }

}