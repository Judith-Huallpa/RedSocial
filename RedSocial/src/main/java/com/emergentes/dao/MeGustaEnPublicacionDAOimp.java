/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Me_Gusta;
import com.emergentes.modelo.Publicaciones;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zerlu
 */
public class MeGustaEnPublicacionDAOimp extends ConexionDB implements MeGustaEnPublicacionDAO {

    @Override
    public void insert(Me_Gusta meGusta) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Me_Gusta_En_Publicaciones (post_id, user_id) VALUES (?, ?);");

            // Obtener el ID de la publicación y el ID del usuario
            int postId = meGusta.getPost_id().getPost_id();
            int userId = meGusta.getUser_id().getUser_id();

            ps.setInt(1, postId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void update(Me_Gusta meGusta) throws Exception {
        // La actualización de un "Me gusta" puede no ser necesaria dependiendo de tus requisitos.
        // Puedes implementarla según tus necesidades específicas.
        // Por ejemplo, puedes permitir a los usuarios cambiar su voto o no permitir actualizaciones.
        throw new UnsupportedOperationException("Not supported yet."); // Cambia esto según tus necesidades.
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Me_Gusta_En_Publicaciones WHERE like_id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public Me_Gusta getById(int id) throws Exception {
        Me_Gusta meGusta = null;
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Me_Gusta_En_Publicaciones WHERE like_id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                meGusta = new Me_Gusta();
                meGusta.setLike_id(rs.getInt("like_id"));

                int userId = rs.getInt("user_id");
                UsuarioDAOimp usuarioDAO = new UsuarioDAOimp(); // Ajusta según tu estructura
                Usuario usuario = usuarioDAO.getById(userId);

                int postId = rs.getInt("post_id");
                PublicacionDAOimp publicacionesDAO = new PublicacionDAOimp(); // Ajusta según tu estructura
                Publicaciones publicacion = publicacionesDAO.getById(postId);

                meGusta.setPost_id(publicacion);
                meGusta.setUser_id(usuario);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return meGusta;
    }

    @Override
    public List<Me_Gusta> getAll() throws Exception {
        List<Me_Gusta> meGustas = new ArrayList<>();
        try {
            conectar();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Me_Gusta_En_Publicaciones;");
                    ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Me_Gusta meGusta = new Me_Gusta();
                    meGusta.setLike_id(rs.getInt("like_id"));

                    int userId = rs.getInt("user_id");
                    Usuario usuario = new Usuario();
                    usuario.setUser_id(userId);

                    int postId = rs.getInt("post_id");
                    Publicaciones publicacion = new Publicaciones();
                    publicacion.setPost_id(postId);

                    meGusta.setPost_id(publicacion);
                    meGusta.setUser_id(usuario);

                    meGustas.add(meGusta);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
        return meGustas;
    }
}
