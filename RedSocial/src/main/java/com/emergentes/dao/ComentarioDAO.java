/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Comentarios;
import java.util.List;

/**
 *
 * @author zerlu
 */
public interface ComentarioDAO {
    public  void insert(Comentarios comentario) throws Exception;
    public  void update(Comentarios comentario) throws Exception;
    public  void delete(int id) throws Exception;
    public Comentarios getById(int id )throws Exception;
    public List<Comentarios>getAll() throws Exception;
    public List<Comentarios> getByPostId(int postId) throws Exception;

}
