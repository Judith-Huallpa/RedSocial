/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Publicaciones;
import java.util.List;

/**
 *
 * @author zerlu
 */
public interface PublicacionDAO {
    public  void insert(Publicaciones publicacion) throws Exception;
    public  void update(Publicaciones publicacion) throws Exception;
    public  void delete(int id) throws Exception;
    public Publicaciones getById(int id )throws Exception;
    public List<Publicaciones>getAll() throws Exception;
}
