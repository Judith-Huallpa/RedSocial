/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Mensajes_Privados;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zerlu
 */
public class MensajePrivadoDAOimp extends ConexionDB implements MensajePrivadoDAO {

    @Override
    public void insert(Mensajes_Privados mensajePrivado) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Mensajes_Privados (user_id_from, user_id_to, contenido_del_mensaje, fecha_envio) VALUES (?, ?, ?, ?);");
            ps.setInt(1, mensajePrivado.getUser_id_from().getUser_id());
            ps.setInt(2, mensajePrivado.getUser_id_to().getUser_id());
            ps.setString(3, mensajePrivado.getContenido_del_mensaje());
            ps.setTimestamp(4, new java.sql.Timestamp(mensajePrivado.getFecha_envio().getTime()));
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void update(Mensajes_Privados mensajePrivado) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE Mensajes_Privados SET contenido_del_mensaje=?, fecha_envio=? WHERE message_id=?;");
            ps.setString(1, mensajePrivado.getContenido_del_mensaje());
            ps.setTimestamp(2, new java.sql.Timestamp(mensajePrivado.getFecha_envio().getTime()));
            ps.setInt(3, mensajePrivado.getMessage_id());
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Mensajes_Privados WHERE message_id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public Mensajes_Privados getById(int id) throws Exception {
        Mensajes_Privados mensajePrivado = null;
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Mensajes_Privados WHERE message_id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                mensajePrivado = new Mensajes_Privados();
                mensajePrivado.setMessage_id(rs.getInt("message_id"));

                int userIdFrom = rs.getInt("user_id_from");
                Usuario usuarioFrom = new Usuario();
                usuarioFrom.setUser_id(userIdFrom);
                mensajePrivado.setUser_id_from(usuarioFrom);

                int userIdTo = rs.getInt("user_id_to");
                Usuario usuarioTo = new Usuario();
                usuarioTo.setUser_id(userIdTo);
                mensajePrivado.setUser_id_to(usuarioTo);

                mensajePrivado.setContenido_del_mensaje(rs.getString("contenido_del_mensaje"));
                mensajePrivado.setFecha_envio(rs.getTimestamp("fecha_envio"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return mensajePrivado;
    }

    @Override
    public List<Mensajes_Privados> getAll() throws Exception {
        List<Mensajes_Privados> mensajesPrivados = new ArrayList<>();
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Mensajes_Privados;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mensajes_Privados mensajePrivado = new Mensajes_Privados();
                mensajePrivado.setMessage_id(rs.getInt("message_id"));

                int userIdFrom = rs.getInt("user_id_from");
                Usuario usuarioFrom = new Usuario();
                usuarioFrom.setUser_id(userIdFrom);
                mensajePrivado.setUser_id_from(usuarioFrom);

                int userIdTo = rs.getInt("user_id_to");
                Usuario usuarioTo = new Usuario();
                usuarioTo.setUser_id(userIdTo);
                mensajePrivado.setUser_id_to(usuarioTo);

                mensajePrivado.setContenido_del_mensaje(rs.getString("contenido_del_mensaje"));
                mensajePrivado.setFecha_envio(rs.getTimestamp("fecha_envio"));

                mensajesPrivados.add(mensajePrivado);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return mensajesPrivados;
    }

}
