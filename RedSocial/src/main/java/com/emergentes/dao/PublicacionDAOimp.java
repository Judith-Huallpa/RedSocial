/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Grupos;
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
public class PublicacionDAOimp extends ConexionDB implements PublicacionDAO {

    @Override
    public void insert(Publicaciones publicacion) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Publicaciones (user_id, grupo_id, contenido_del_mensaje, foto_de_PUBLISH, fecha_publicacion) VALUES (?, ?, ?, ?, ?);");

            // Obtener los ID de Usuario y Grupos
            int userId = publicacion.getUser_id().getUser_id();
            int grupoId = publicacion.getGrupo_id().getGroup_id();

            ps.setInt(1, userId);
            ps.setInt(2, grupoId);
            ps.setString(3, publicacion.getContenido_del_mensaje());
            ps.setString(4, publicacion.getFoto_de_publish());
            ps.setTimestamp(5, new java.sql.Timestamp(publicacion.getFecha_publicacion().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void update(Publicaciones publicacion) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE Publicaciones SET contenido_del_mensaje=?, foto_de_PUBLISH=?, fecha_publicacion=? WHERE post_id=?;");
            ps.setString(1, publicacion.getContenido_del_mensaje());
            ps.setString(2, publicacion.getFoto_de_publish());
            ps.setTimestamp(3, new java.sql.Timestamp(publicacion.getFecha_publicacion().getTime()));
            ps.setInt(4, publicacion.getPost_id());
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Publicaciones WHERE post_id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public Publicaciones getById(int id) throws Exception {
        Publicaciones publicacion = null;
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Publicaciones WHERE post_id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                publicacion = new Publicaciones();
                publicacion.setPost_id(rs.getInt("post_id"));

                // Crear instancias de Usuario y Grupos y establecerlas en publicacion
                Usuario user = new Usuario();
                user.setUser_id(rs.getInt("user_id"));
                publicacion.setUser_id(user);

                Grupos grupo = new Grupos();
                grupo.setGroup_id(rs.getInt("grupo_id"));
                publicacion.setGrupo_id(grupo);

                publicacion.setContenido_del_mensaje(rs.getString("contenido_del_mensaje"));
                publicacion.setFoto_de_publish(rs.getString("foto_de_publish"));
                publicacion.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return publicacion;
    }

    @Override
    public List<Publicaciones> getAll() throws Exception {
        List<Publicaciones> publicaciones = new ArrayList<>();
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT\n"
                    + "    p.*,\n"
                    + "    g.*,\n"
                    + "    u.*,\n"
                    + "    COUNT(DISTINCT c.comment_id) AS cantidad_comentarios,\n"
                    + "    COUNT(DISTINCT m.like_id) AS cantidad_likes\n"
                    + "FROM\n"
                    + "    Publicaciones p\n"
                    + "    LEFT JOIN Comentarios c ON p.post_id = c.post_id\n"
                    + "    LEFT JOIN Me_Gusta_En_Publicaciones m ON p.post_id = m.post_id\n"
                    + "    LEFT JOIN Grupos g ON p.grupo_id = g.group_id\n"
                    + "    LEFT JOIN Usuarios u ON p.user_id = u.user_id\n"
                    + "GROUP BY\n"
                    + "    p.post_id\n"
                    + "ORDER BY\n"
                    + "    p.fecha_publicacion DESC;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Publicaciones publicacion = new Publicaciones();
                publicacion.setPost_id(rs.getInt("post_id"));

                Usuario user = new Usuario();
                user.setUser_id(rs.getInt("user_id"));
                user.setNombre(rs.getString("nombre")); // Cambié "nombre_usuario" a "user_nombre"
                publicacion.setUser_id(user);

                Grupos grupo = new Grupos();
                grupo.setGroup_id(rs.getInt("group_id"));
                grupo.setNombreDelGrupo(rs.getString("nombre_del_grupo"));
                publicacion.setGrupo_id(grupo);

                publicacion.setContenido_del_mensaje(rs.getString("contenido_del_mensaje"));
                publicacion.setFoto_de_publish(rs.getString("foto_de_publish"));
                publicacion.setFecha_publicacion(rs.getTimestamp("fecha_publicacion"));

                // Nueva columna para la cantidad de "Me gusta"
                publicacion.setCantidadLikes(rs.getInt("cantidad_likes"));
                publicacion.setCantidadComentario(rs.getInt("cantidad_comentarios"));

                publicaciones.add(publicacion);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return publicaciones;
    }

}
