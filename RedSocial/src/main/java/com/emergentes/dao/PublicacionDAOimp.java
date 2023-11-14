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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Publicaciones;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Publicaciones publicacion = new Publicaciones();
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
