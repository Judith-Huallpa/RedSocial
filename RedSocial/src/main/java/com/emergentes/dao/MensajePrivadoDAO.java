/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Mensajes_Privados;
import java.util.List;

/**
 *
 * @author zerlu
 */
public interface MensajePrivadoDAO {
    public void insert(Mensajes_Privados mensajePrivado) throws Exception;
    public void update(Mensajes_Privados mensajePrivado) throws Exception;
    public void delete(int id) throws Exception;
    public Mensajes_Privados getById(int id )throws Exception;
    public List<Mensajes_Privados>getAll() throws Exception;
}
