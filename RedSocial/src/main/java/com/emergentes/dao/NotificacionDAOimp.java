/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Notificaciones;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zerlu
 */
public class NotificacionDAOimp extends ConexionDB implements NotificacionDAO {

    @Override
    public void insert(Notificaciones notificaciones) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Notificaciones (user_id, tipo_de_notificacion, contenido_de_notificacion, fecha_notificacion) VALUES (?, ?, ?, ?);");
            
            int userId = notificaciones.getUser_id().getUser_id();
            ps.setInt(1, userId);
            
            ps.setString(2, notificaciones.getTipo_de_notificacion());
            ps.setString(3, notificaciones.getContenido_de_notificacion());
            ps.setTimestamp(4, new java.sql.Timestamp(notificaciones.getFechaNotificacion().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void update(Notificaciones notificaciones) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE Notificaciones SET tipo_de_notificacion=?, contenido_de_notificacion=?, fecha_notificacion=? WHERE notification_id=?;");
            ps.setString(1, notificaciones.getTipo_de_notificacion());
            ps.setString(2, notificaciones.getContenido_de_notificacion());
            ps.setTimestamp(3, new java.sql.Timestamp(notificaciones.getFechaNotificacion().getTime()));
            ps.setInt(4, notificaciones.getNotification_id());
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Notificaciones WHERE notification_id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public Notificaciones getById(int id) throws Exception {
        Notificaciones notificacion = null;
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Notificaciones WHERE notification_id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                notificacion = new Notificaciones();
                notificacion.setNotification_id(rs.getInt("notification_id"));
                
                int userId = rs.getInt("user_id");
                Usuario usuario = new Usuario();
                usuario.setUser_id(userId);
                notificacion.setUser_id(usuario);

                notificacion.setTipo_de_notificacion(rs.getString("tipo_de_notificacion"));
                notificacion.setContenido_de_notificacion(rs.getString("contenido_de_notificacion"));
                notificacion.setFechaNotificacion(rs.getTimestamp("fecha_notificacion"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return notificacion;
    }

    @Override
    public List<Notificaciones> getAll() throws Exception {
        List<Notificaciones> notificaciones = new ArrayList<>();
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Notificaciones;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notificaciones notificacion = new Notificaciones();
                notificacion.setNotification_id(rs.getInt("notification_id"));
                
                int userId = rs.getInt("user_id");
                Usuario usuario = new Usuario();
                usuario.setUser_id(userId);
                notificacion.setUser_id(usuario);
                
                notificacion.setTipo_de_notificacion(rs.getString("tipo_de_notificacion"));
                notificacion.setContenido_de_notificacion(rs.getString("contenido_de_notificacion"));
                notificacion.setFechaNotificacion(rs.getTimestamp("fecha_notificacion"));

                notificaciones.add(notificacion);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return notificaciones;
    }

}
