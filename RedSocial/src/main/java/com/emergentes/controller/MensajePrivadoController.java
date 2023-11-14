/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.MensajePrivadoDAO;
import com.emergentes.dao.MensajePrivadoDAOimp;
import com.emergentes.modelo.Mensajes_Privados;
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
@WebServlet(name = "MensajePrivadoController", urlPatterns = {"/MensajePrivadoController"})
public class MensajePrivadoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MensajePrivadoDAO dao = new MensajePrivadoDAOimp();
            int id = 0;
            Mensajes_Privados mensajePrivado = new Mensajes_Privados();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("mensajePrivado", mensajePrivado);
                    request.getRequestDispatcher("frmMensajesPrivados.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    mensajePrivado = dao.getById(id);
                    request.setAttribute("mensajePrivado", mensajePrivado);
                    request.getRequestDispatcher("frmMensajesPrivados.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/MensajePrivadoController");
                    break;
                case "view":
                    List<Mensajes_Privados> lista = dao.getAll();
                    request.setAttribute("mensajesPrivados", lista);
                    request.getRequestDispatcher("mensajesPrivados.jsp").forward(request, response);
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
        int userIdFrom = Integer.parseInt(request.getParameter("userIdFrom"));
        int userIdTo = Integer.parseInt(request.getParameter("userIdTo"));
        String contenidoDelMensaje = request.getParameter("contenidoDelMensaje");
        Mensajes_Privados mensajePrivado = new Mensajes_Privados();

        mensajePrivado.setMessage_id(id);

        // Crear objetos Usuario con los ID correspondientes
        Usuario usuarioFrom = new Usuario();
        usuarioFrom.setUser_id(userIdFrom);

        Usuario usuarioTo = new Usuario();
        usuarioTo.setUser_id(userIdTo);

        mensajePrivado.setUser_id_from(usuarioFrom);
        mensajePrivado.setUser_id_to(usuarioTo);

        mensajePrivado.setContenido_del_mensaje(contenidoDelMensaje);

        if (id == 0) {
            // Nuevo
            try {
                MensajePrivadoDAO dao = new MensajePrivadoDAOimp();
                dao.insert(mensajePrivado);
                response.sendRedirect(request.getContextPath() + "/MensajePrivadoController");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else {
            // Edición
            try {
                MensajePrivadoDAO dao = new MensajePrivadoDAOimp();
                dao.update(mensajePrivado);
                response.sendRedirect(request.getContextPath() + "/MensajePrivadoController");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
