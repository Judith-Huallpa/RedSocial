/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Notificaciones;
import java.util.List;

/**
 *
 * @author zerlu
 */
public interface NotificacionDAO {
    public void insert(Notificaciones notificaciones) throws Exception;
    public void update(Notificaciones notificaciones) throws Exception;
    public void delete(int id) throws Exception;
    public Notificaciones getById(int id )throws Exception;
    public List<Notificaciones>getAll() throws Exception;
}
