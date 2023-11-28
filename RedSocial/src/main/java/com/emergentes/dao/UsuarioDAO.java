/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Usuario;
import java.util.List;

/**
 *
 * @author zerlu
 */
public interface UsuarioDAO {
    public  void insert(Usuario usuario) throws Exception;
    public  void update(Usuario usuario) throws Exception;
    public  void delete(int id) throws Exception;
    public Usuario getById(int id )throws Exception;
    public List<Usuario>getAll() throws Exception;
    List<Usuario> buscarUsuarios(String terminoBusqueda) throws Exception;
}
