package com.emergentes.modelo;
public class Amigos {
    private int friendship_id;
    private Usuario user_id1;
    private Usuario user_id2;
    private String estado_amistad;

    public int getFriendship_id() {
        return friendship_id;
    }

    public void setFriendship_id(int friendship_id) {
        this.friendship_id = friendship_id;
    }

    public Usuario getUser_id1() {
        return user_id1;
    }

    public void setUser_id1(Usuario user_id1) {
        this.user_id1 = user_id1;
    }

    public Usuario getUser_id2() {
        return user_id2;
    }

    public void setUser_id2(Usuario user_id2) {
        this.user_id2 = user_id2;
    }

    public String getEstado_amistad() {
        return estado_amistad;
    }

    public void setEstado_amistad(String estado_amistad) {
        this.estado_amistad = estado_amistad;
    }

    @Override
    public String toString() {
        return "Amigos{" + "friendship_id=" + friendship_id + ", user_id1=" + user_id1 + ", user_id2=" + user_id2 + ", estado_amistad=" + estado_amistad + '}';
    }
    
}