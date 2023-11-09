package com.emergentes.modelo;
public class Me_Gusta {
    private int like_id;
    private Publicaciones post_id;
    private Usuario user_id;

    public int getLike_id() {
        return like_id;
    }

    public void setLike_id(int like_id) {
        this.like_id = like_id;
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

    @Override
    public String toString() {
        return "Me_Gusta{" + "like_id=" + like_id + ", post_id=" + post_id + ", user_id=" + user_id + '}';
    }
    
}