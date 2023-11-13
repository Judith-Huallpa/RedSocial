/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Amigos;
import java.util.List;

/**
 *
 * @author zerlu
 */
public interface AmigoDAO {
    public  void insert(Amigos amigo) throws Exception;
    public  void update(Amigos amigo) throws Exception;
    public  void delete(int id) throws Exception;
    public Amigos getById(int id )throws Exception;
    public List<Amigos>getAll() throws Exception;
}
