package com.emergentes.modelo;
public class Miembros_Grupo {
    private int membership_id;
    private Grupos group_id;
    private Usuario user_id;
    private String rol_Usuario_Grupo;

    public int getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(int membership_id) {
        this.membership_id = membership_id;
    }

    public Grupos getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Grupos group_id) {
        this.group_id = group_id;
    }

    public Usuario getUser_id() {
        return user_id;
    }

    public void setUser_id(Usuario user_id) {
        this.user_id = user_id;
    }

    public String getRol_Usuario_Grupo() {
        return rol_Usuario_Grupo;
    }

    public void setRol_Usuario_Grupo(String rol_Usuario_Grupo) {
        this.rol_Usuario_Grupo = rol_Usuario_Grupo;
    }

    @Override
    public String toString() {
        return "Miembros_Grupo{" + "membership_id=" + membership_id + ", group_id=" + group_id + ", user_id=" + user_id + ", rol_Usuario_Grupo=" + rol_Usuario_Grupo + '}';
    }
    
}