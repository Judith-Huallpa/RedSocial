/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.conexion.ConexionDB;
import com.emergentes.modelo.Grupos;
import com.emergentes.modelo.Miembros_Grupo;
import com.emergentes.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zerlu
 */
public class MiembrosDeGrupoDAOimp extends ConexionDB implements MiembrosDeGrupoDAO {

    @Override
    public void insert(Miembros_Grupo miembroGrupo) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Miembros_de_Grupos (group_id, user_id, rol_de_usuario_en_el_grupo) VALUES (?, ?, ?);");

            int grupoId = miembroGrupo.getGroup_id().getGroup_id();
            ps.setInt(1, grupoId);

            int userId = miembroGrupo.getUser_id().getUser_id();
            ps.setInt(2, userId);

            ps.setString(3, miembroGrupo.getRol_Usuario_Grupo());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public void update(Miembros_Grupo miembroGrupo) throws Exception {
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("UPDATE Miembros_de_Grupos SET rol_de_usuario_en_el_grupo=? WHERE membership_id=?;");
            ps.setString(1, miembroGrupo.getRol_Usuario_Grupo());
            ps.setInt(2, miembroGrupo.getMembership_id());
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Miembros_de_Grupos WHERE membership_id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    @Override
    public Miembros_Grupo getById(int id) throws Exception {
        Miembros_Grupo miembroGrupo = null;
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Miembros_de_Grupos WHERE membership_id=?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                miembroGrupo = new Miembros_Grupo();
                miembroGrupo.setMembership_id(rs.getInt("membership_id"));

                // Crear instancia de Grupos y establecerla en miembroGrupo
                Grupos grupo = new Grupos();
                grupo.setGroup_id(rs.getInt("group_id"));
                miembroGrupo.setGroup_id(grupo);

                // Crear instancia de Usuario y establecerla en miembroGrupo
                Usuario usuario = new Usuario();
                usuario.setUser_id(rs.getInt("user_id"));
                miembroGrupo.setUser_id(usuario);

                miembroGrupo.setRol_Usuario_Grupo(rs.getString("rol_de_usuario_en_el_grupo"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return miembroGrupo;
    }

    @Override
    public List<Miembros_Grupo> getAll() throws Exception {
        List<Miembros_Grupo> miembrosGrupos = new ArrayList<>();
        try {
            conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Miembros_de_Grupos;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Miembros_Grupo miembroGrupo = new Miembros_Grupo();
                miembroGrupo.setMembership_id(rs.getInt("membership_id"));

                // Crear instancia de Grupos y establecerla en miembroGrupo
                Grupos grupo = new Grupos();
                grupo.setGroup_id(rs.getInt("group_id"));
                miembroGrupo.setGroup_id(grupo);

                // Crear instancia de Usuario y establecerla en miembroGrupo
                Usuario usuario = new Usuario();
                usuario.setUser_id(rs.getInt("user_id"));
                miembroGrupo.setUser_id(usuario);

                miembroGrupo.setRol_Usuario_Grupo(rs.getString("rol_de_usuario_en_el_grupo"));

                miembrosGrupos.add(miembroGrupo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

        return miembrosGrupos;
    }

}
