/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.NotificacionDAO;
import com.emergentes.dao.NotificacionDAOimp;
import com.emergentes.modelo.Notificaciones;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zerlu
 */
@WebServlet(name = "NotificacionController", urlPatterns = {"/NotificacionController"})
public class NotificacionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            NotificacionDAO dao = new NotificacionDAOimp();
            int id = 0;
            Notificaciones notificacion = new Notificaciones();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("notificacion", notificacion);
                    request.getRequestDispatcher("frmNotificaciones.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    notificacion = dao.getById(id);
                    request.setAttribute("notificacion", notificacion);
                    request.getRequestDispatcher("frmNotificaciones.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/NotificacionController");
                    break;
                case "view":
                    List<Notificaciones> lista = dao.getAll();
                    request.setAttribute("notificaciones", lista);
                    request.getRequestDispatcher("notificaciones.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String tipoDeNotificacion = request.getParameter("tipoDeNotificacion");
        String contenidoDeNotificacion = request.getParameter("contenidoDeNotificacion");
        Notificaciones notificacion = new Notificaciones();

        notificacion.setNotification_id(id);

        // Crear objeto Usuario con el ID correspondiente
        Usuario usuario = new Usuario();
        usuario.setUser_id(userId);

        notificacion.setUser_id(usuario);
        notificacion.setTipo_de_notificacion(tipoDeNotificacion);
        notificacion.setContenido_de_notificacion(contenidoDeNotificacion);

        if (id == 0) {
            // Nuevo
            try {
                NotificacionDAO dao = new NotificacionDAOimp();
                dao.insert(notificacion);
                response.sendRedirect(request.getContextPath() + "/NotificacionController");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else {
            // Edición
            try {
                NotificacionDAO dao = new NotificacionDAOimp();
                dao.update(notificacion);
                response.sendRedirect(request.getContextPath() + "/NotificacionController");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}
