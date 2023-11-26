/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zerlu
 */
public class UsuarioDAOimp extends ConexionDB implements UsuarioDAO {

    @Override
    public void insert(Usuario usuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO Usuarios (user_id, nombre, correo_electronico, contrasena, fecha_registro) VALUES (?, ?, ?, ?, ?);");
            ps.setInt(1, usuario.getUser_id());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getCorreo_electronico());
            ps.setString(4, usuario.getContrasena());
            ps.setDate(5, new java.sql.Date(usuario.getFecha_registro().getTime())); // Suponiendo que fecha_registro es de tipo java.util.Date
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE Usuarios SET nombre=?, correo_electronico=?, contrasena=?, fecha_registro=? WHERE user_id=?;");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo_electronico());
            ps.setString(3, usuario.getContrasena());
            ps.setDate(4, new java.sql.Date(usuario.getFecha_registro().getTime())); // Suponiendo que fecha_registro es de tipo java.util.Date
            ps.setInt(5, usuario.getUser_id());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM Usuarios WHERE user_id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Usuario getById(int id) throws Exception {
        Usuario usuario = new Usuario();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM Usuarios WHERE user_id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setUser_id(rs.getInt("user_id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo_electronico(rs.getString("correo_electronico"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setFecha_registro(rs.getDate("fecha_registro"));

            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

        return usuario;
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> usuarios = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM Usuarios;");
            ResultSet rs = ps.executeQuery();

            usuarios = new ArrayList<Usuario>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUser_id(rs.getInt("user_id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo_electronico(rs.getString("correo_electronico"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setFecha_registro(rs.getDate("fecha_registro"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return usuarios;
    }

    public boolean validarCredenciales(String correoElectronico, String contrasena) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM Usuarios WHERE correo_electronico=? AND contrasena=?;");
            ps.setString(1, correoElectronico);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            // Devuelve true si hay al menos una fila (credenciales válidas), false si no hay filas
            return rs.next();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    public Usuario getUsuarioByCorreoElectronico(String correoElectronico) {
        Usuario usuario = null;
        try {
            this.conectar();
            String sql = "SELECT * FROM Usuarios WHERE correo_electronico = ?";
            try (PreparedStatement ps = this.conn.prepareStatement(sql)) {
                ps.setString(1, correoElectronico);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        usuario = new Usuario();
                        usuario.setUser_id(rs.getInt("user_id"));
                        usuario.setNombre(rs.getString("nombre"));
                        usuario.setCorreo_electronico(rs.getString("correo_electronico"));
                        usuario.setContrasena(rs.getString("contrasena"));
                        usuario.setFecha_registro(rs.getDate("fecha_registro"));
                        // Agrega más asignaciones según las columnas de tu tabla
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return usuario;
    }

    public Usuario getUsuarioConPerfilByCorreoElectronico(String correoElectronico) {
        Usuario usuario = null;
        try {
            this.conectar();
            String sql = "SELECT u.*, p.profile_id, p.foto_de_perfil, p.descripcion FROM Usuarios u LEFT JOIN Perfiles_Usuario p ON u.user_id = p.user_id WHERE u.correo_electronico = ?";
            try (PreparedStatement ps = this.conn.prepareStatement(sql)) {
                ps.setString(1, correoElectronico);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        usuario = new Usuario();
                        usuario.setUser_id(rs.getInt("user_id"));
                        usuario.setNombre(rs.getString("nombre"));
                        usuario.setCorreo_electronico(rs.getString("correo_electronico"));
                        usuario.setContrasena(rs.getString("contrasena"));
                        usuario.setFecha_registro(rs.getDate("fecha_registro"));

                        // Propiedades del perfil
                        int profileId = rs.getInt("profile_id");
                        String fotoPerfil = rs.getString("foto_de_perfil");
                        String descripcionPerfil = rs.getString("descripcion");

                        // Ahora puedes hacer lo que quieras con las propiedades del perfil
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return usuario;
    }

}
