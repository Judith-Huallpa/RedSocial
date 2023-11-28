/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Amigos;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zerlu
 */
public class AmigoDAOimp extends ConexionDB implements AmigoDAO {

    @Override
    public void insert(Amigos amigo) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Amigos (user_id1, user_id2, estado_amistad) VALUES (?, ?, ?);");
            ps.setInt(1, amigo.getUser_id1().getUser_id());
            ps.setInt(2, amigo.getUser_id2().getUser_id());
            ps.setString(3, amigo.getEstado_amistad());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void update(Amigos amigo) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE Amigos SET estado_amistad=? WHERE friendship_id=?;");
            ps.setString(1, amigo.getEstado_amistad());
            ps.setInt(2, amigo.getFriendship_id());
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Amigos WHERE friendship_id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public Amigos getById(int id) throws Exception {
        Amigos amigo = null;
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Amigos WHERE friendship_id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                amigo = new Amigos();
                amigo.setFriendship_id(rs.getInt("friendship_id"));

                Usuario user1 = new Usuario();
                user1.setUser_id(rs.getInt("user_id1"));
                amigo.setUser_id1(user1);

                Usuario user2 = new Usuario();
                user2.setUser_id(rs.getInt("user_id2"));
                amigo.setUser_id2(user2);

                amigo.setEstado_amistad(rs.getString("estado_amistad"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return amigo;
    }

    @Override
    public List<Amigos> getAll() throws Exception {
        List<Amigos> amigos = new ArrayList<>();
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Amigos;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Amigos amigo = new Amigos();
                amigo.setFriendship_id(rs.getInt("friendship_id"));

                // Crear instancias de Usuario y establecerlas en amigo
                Usuario user1 = new Usuario();
                user1.setUser_id(rs.getInt("user_id1"));
                amigo.setUser_id1(user1);

                Usuario user2 = new Usuario();
                user2.setUser_id(rs.getInt("user_id2"));
                amigo.setUser_id2(user2);

                amigo.setEstado_amistad(rs.getString("estado_amistad"));

                amigos.add(amigo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return amigos;
    }

    @Override
    public List<Amigos> getAmigosConSolicitudesPendientes(int userId) throws Exception {
        List<Amigos> amigos = new ArrayList<>();
        try {
            this.conectar();
            // Seleccionar amigos donde la solicitud esté pendiente y el usuario sea el usuario actual
            String sql = "SELECT Amigos.*, U1.nombre AS nombre_user1, U2.nombre AS nombre_user2 "
                    + "FROM Amigos "
                    + "JOIN Usuarios U1 ON Amigos.user_id1 = U1.user_id "
                    + "JOIN Usuarios U2 ON Amigos.user_id2 = U2.user_id "
                    + "WHERE (Amigos.user_id1 = ? ) AND estado_amistad = 'pendiente';";
            try (PreparedStatement ps = this.conn.prepareStatement(sql)) {
                ps.setInt(1, userId);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Amigos amigo = new Amigos();
                        amigo.setFriendship_id(rs.getInt("friendship_id"));

                        // Crear instancias de Usuario y establecerlas en amigo
                        Usuario user1 = new Usuario();
                        user1.setUser_id(rs.getInt("user_id1"));
                        user1.setNombre(rs.getString("nombre_user1"));
                        amigo.setUser_id1(user1);

                        Usuario user2 = new Usuario();
                        user2.setUser_id(rs.getInt("user_id2"));
                        user2.setNombre(rs.getString("nombre_user2"));
                        amigo.setUser_id2(user2);

                        amigo.setEstado_amistad(rs.getString("estado_amistad"));

                        amigos.add(amigo);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return amigos;
    }

    @Override
    public List<Amigos> getAmigosConSolicitudesAceptadas(int userId) throws Exception {
        List<Amigos> amigos = new ArrayList<>();
        try {
            conectar();

            // Seleccionar amigos donde la solicitud esté aceptada y el usuario sea el usuario actual
            String sql = "SELECT Amigos.*, U1.nombre AS nombre_user1, U2.nombre AS nombre_user2 "
                    + "FROM Amigos "
                    + "JOIN Usuarios U1 ON Amigos.user_id1 = U1.user_id "
                    + "JOIN Usuarios U2 ON Amigos.user_id2 = U2.user_id "
                    + "WHERE (user_id1 = ? OR user_id2 = ?) AND estado_amistad = 'aceptada';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Amigos amigo = new Amigos();
                amigo.setFriendship_id(rs.getInt("friendship_id"));

                // Crear instancias de Usuario y establecerlas en amigo
                Usuario user1 = new Usuario();
                user1.setUser_id(rs.getInt("user_id1"));
                user1.setNombre(rs.getString("nombre_user1"));
                amigo.setUser_id1(user1);

                Usuario user2 = new Usuario();
                user2.setUser_id(rs.getInt("user_id2"));
                user2.setNombre(rs.getString("nombre_user2"));
                amigo.setUser_id2(user2);

                amigo.setEstado_amistad(rs.getString("estado_amistad"));

                amigos.add(amigo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return amigos;
    }

}
