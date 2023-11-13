/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Perfil_Usuario;
import java.util.List;

/**
 *
 * @author zerlu
 */
public interface PerfilUsuarioDAO {
    public  void insert(Perfil_Usuario perfilUsuario) throws Exception;
    public  void update(Perfil_Usuario perfilUsuario) throws Exception;
    public  void delete(int id) throws Exception;
    public Perfil_Usuario getById(int id )throws Exception;
    public List<Perfil_Usuario>getAll() throws Exception;
}
