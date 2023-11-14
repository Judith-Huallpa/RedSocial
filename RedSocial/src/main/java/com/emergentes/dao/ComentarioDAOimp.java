/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Comentarios;
import com.emergentes.modelo.Publicaciones;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zerlu
 */
public class ComentarioDAOimp extends ConexionDB implements ComentarioDAO {

    @Override
    public void insert(Comentarios comentario) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Comentarios (post_id, user_id, contenido_del_comentario, fecha_comentario) VALUES (?, ?, ?, ?);");
            ps.setInt(1, comentario.getPost_id().getPost_id());
            ps.setInt(2, comentario.getUser_id().getUser_id());
            ps.setString(3, comentario.getContenido_del_comentario());
            ps.setTimestamp(4, new java.sql.Timestamp(comentario.getFecha_comentario().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void update(Comentarios comentario) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE Comentarios SET contenido_del_comentario=?, fecha_comentario=? WHERE comment_id=?;");
            ps.setString(1, comentario.getContenido_del_comentario());
            ps.setTimestamp(2, new java.sql.Timestamp(comentario.getFecha_comentario().getTime()));
            ps.setInt(3, comentario.getCommentId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Comentarios WHERE comment_id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public Comentarios getById(int id) throws Exception {
        Comentarios comentario = null;
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Comentarios WHERE comment_id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                comentario = new Comentarios();
                comentario.setCommentId(rs.getInt("comment_id"));

                // Crear instancias de Publicaciones y Usuario y establecerlas en comentario
                Publicaciones post = new Publicaciones();
                post.setPost_id(rs.getInt("post_id"));
                comentario.setPost_id(post);

                Usuario user = new Usuario();
                user.setUser_id(rs.getInt("user_id"));
                comentario.setUser_id(user);

                comentario.setContenido_del_comentario(rs.getString("contenido_del_comentario"));
                comentario.setFecha_comentario(rs.getTimestamp("fecha_comentario"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return comentario;
    }

    @Override
    public List<Comentarios> getAll() throws Exception {
        List<Comentarios> comentarios = new ArrayList<>();
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Comentarios;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comentarios comentario = new Comentarios();
                comentario.setCommentId(rs.getInt("comment_id"));

                // Crear instancias de Publicaciones y Usuario y establecerlas en comentario
                Publicaciones post = new Publicaciones();
                post.setPost_id(rs.getInt("post_id"));
                comentario.setPost_id(post);

                Usuario user = new Usuario();
                user.setUser_id(rs.getInt("user_id"));
                comentario.setUser_id(user);

                comentario.setContenido_del_comentario(rs.getString("contenido_del_comentario"));
                comentario.setFecha_comentario(rs.getTimestamp("fecha_comentario"));

                comentarios.add(comentario);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return comentarios;
    }

}
