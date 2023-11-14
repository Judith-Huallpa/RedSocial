/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Grupos;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zerlu
 */
public class GrupoDAOimp extends ConexionDB implements GrupoDAO {

    @Override
    public void insert(Grupos grupo) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Grupos (nombre_del_grupo, descripcion, user_id) VALUES (?, ?, ?);");
            ps.setString(1, grupo.getNombreDelGrupo());
            ps.setString(2, grupo.getDescripcion());

            // Obtener el ID del usuario
            int userId = grupo.getUser_id().getUser_id();
            ps.setInt(3, userId);

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void update(Grupos grupo) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE Grupos SET nombre_del_grupo=?, descripcion=?, user_id=? WHERE group_id=?;");
            ps.setString(1, grupo.getNombreDelGrupo());
            ps.setString(2, grupo.getDescripcion());

            // Obtener el ID del usuario
            int userId = grupo.getUser_id().getUser_id();
            ps.setInt(3, userId);

            // Establecer el ID del grupo
            ps.setInt(4, grupo.getGroup_id());

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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Grupos WHERE group_id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public Grupos getById(int id) throws Exception {
        Grupos grupo = null;
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Grupos WHERE group_id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                grupo = new Grupos();
                grupo.setGroup_id(rs.getInt("group_id"));
                grupo.setNombreDelGrupo(rs.getString("nombre_del_grupo"));
                grupo.setDescripcion(rs.getString("descripcion"));

                // Obtener el objeto Usuario
                Usuario usuario = new Usuario();
                usuario.setUser_id(rs.getInt("user_id"));
                grupo.setUser_id(usuario);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return grupo;
    }

    @Override
    public List<Grupos> getAll() throws Exception {
        List<Grupos> grupos = new ArrayList<>();
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Grupos;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Grupos grupo = new Grupos();
                grupo.setGroup_id(rs.getInt("group_id"));
                grupo.setNombreDelGrupo(rs.getString("nombre_del_grupo"));
                grupo.setDescripcion(rs.getString("descripcion"));

                // Obtener el objeto Usuario
                Usuario usuario = new Usuario();
                usuario.setUser_id(rs.getInt("user_id"));
                grupo.setUser_id(usuario);

                grupos.add(grupo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return grupos;
    }

}
