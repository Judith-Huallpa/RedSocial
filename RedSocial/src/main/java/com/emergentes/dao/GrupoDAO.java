/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Grupos;
import java.util.List;

/**
 *
 * @author zerlu
 */
public interface GrupoDAO {
    public  void insert(Grupos grupo) throws Exception;
    public  void update(Grupos grupo) throws Exception;
    public  void delete(int id) throws Exception;
    public Grupos getById(int id )throws Exception;
    public List<Grupos>getAll() throws Exception;
}
