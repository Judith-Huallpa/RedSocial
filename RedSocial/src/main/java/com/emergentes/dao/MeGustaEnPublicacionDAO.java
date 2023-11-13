/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Me_Gusta;
import java.util.List;

/**
 *
 * @author zerlu
 */
public interface MeGustaEnPublicacionDAO {
    public  void insert(Me_Gusta meGusta) throws Exception;
    public  void update(Me_Gusta meGusta) throws Exception;
    public  void delete(int id) throws Exception;
    public Me_Gusta getById(int id )throws Exception;
    public List<Me_Gusta>getAll() throws Exception;
}
