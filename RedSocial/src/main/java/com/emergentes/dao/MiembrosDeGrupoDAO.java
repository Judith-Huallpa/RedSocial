/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Miembros_Grupo;
import java.util.List;

/**
 *
 * @author zerlu
 */
public interface MiembrosDeGrupoDAO {
    public void insert(Miembros_Grupo miembroGrupo) throws Exception;
    public void update(Miembros_Grupo miembroGrupo) throws Exception;
    public void delete(int id) throws Exception;
    public Miembros_Grupo getById(int id )throws Exception;
    public List<Miembros_Grupo>getAll() throws Exception;
}
