/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Perfil_Usuario;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zerlu
 */
public class PerfilUsuarioDAOimp extends ConexionDB implements PerfilUsuarioDAO {

    @Override
    public void insert(Perfil_Usuario perfilUsuario) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Perfiles_Usuario (user_id, foto_de_perfil, descripcion) VALUES (?, ?, ?);");
            ps.setInt(1, perfilUsuario.getUser_id().getUser_id());
            ps.setString(2, perfilUsuario.getFoto_de_perfil());
            ps.setString(3, perfilUsuario.getDescripcion());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza de la excepción
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void update(Perfil_Usuario perfilUsuario) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE Perfiles_Usuario SET foto_de_perfil=?, descripcion=? WHERE profile_id=?;");
            ps.setString(1, perfilUsuario.getFoto_de_perfil());
            ps.setString(2, perfilUsuario.getDescripcion());
            ps.setInt(3, perfilUsuario.getProfile_Id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza de la excepción
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Perfiles_Usuario WHERE profile_id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza de la excepción
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public Perfil_Usuario getById(int id) throws Exception {
        Perfil_Usuario perfilUsuario = null;
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Perfiles_Usuario WHERE profile_id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                perfilUsuario = new Perfil_Usuario();
                perfilUsuario.setProfile_Id(rs.getInt("profile_id"));

                Usuario usuario = new Usuario();
                usuario.setUser_id(rs.getInt("user_id"));
                perfilUsuario.setUser_id(usuario);

                perfilUsuario.setFoto_de_perfil(rs.getString("foto_de_perfil"));
                perfilUsuario.setDescripcion(rs.getString("descripcion"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza de la excepción
            throw e;
        } finally {
            desconectar();
        }

        return perfilUsuario;
    }

    @Override
    public List<Perfil_Usuario> getAll() throws Exception {
        List<Perfil_Usuario> perfilesUsuarios = new ArrayList<>();
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Perfiles_Usuario;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Perfil_Usuario perfilUsuario = new Perfil_Usuario();
                perfilUsuario.setProfile_Id(rs.getInt("profile_id"));

                Usuario usuario = new Usuario();
                usuario.setUser_id(rs.getInt("user_id"));
                perfilUsuario.setUser_id(usuario);

                perfilUsuario.setFoto_de_perfil(rs.getString("foto_de_perfil"));
                perfilUsuario.setDescripcion(rs.getString("descripcion"));

                perfilesUsuarios.add(perfilUsuario);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza de la excepción
            throw e;
        } finally {
            desconectar();
        }

        return perfilesUsuarios;
    }
}
