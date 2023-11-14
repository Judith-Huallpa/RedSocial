/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.MeGustaEnPublicacionDAO;
import com.emergentes.dao.MeGustaEnPublicacionDAOimp;
import com.emergentes.modelo.Me_Gusta;
import com.emergentes.modelo.Publicaciones;
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
@WebServlet(name = "MeGustaController", urlPatterns = {"/MeGustaController"})
public class MeGustaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MeGustaEnPublicacionDAO dao = new MeGustaEnPublicacionDAOimp();
            int id = 0;
            Me_Gusta meGusta = new Me_Gusta();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("meGusta", meGusta);
                    request.getRequestDispatcher("frmMeGusta.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    meGusta = dao.getById(id);
                    request.setAttribute("meGusta", meGusta);
                    request.getRequestDispatcher("frmMeGusta.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/MeGustaController");
                    break;
                case "view":
                    List<Me_Gusta> lista = dao.getAll();
                    request.setAttribute("meGustas", lista);
                    request.getRequestDispatcher("meGustas.jsp").forward(request, response);
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
        int postId = Integer.parseInt(request.getParameter("postId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        Me_Gusta meGusta = new Me_Gusta();

        meGusta.setLike_id(id);

        // Crear objetos Publicaciones y Usuario con los ID correspondientes
        Publicaciones publicacion = new Publicaciones();
        publicacion.setPost_id(postId);
        meGusta.setPost_id(publicacion);

        Usuario usuario = new Usuario();
        usuario.setUser_id(userId);
        meGusta.setUser_id(usuario);

        if (id == 0) {
            // Nuevo
            try {
                MeGustaEnPublicacionDAO dao = new MeGustaEnPublicacionDAOimp();
                dao.insert(meGusta);
                response.sendRedirect(request.getContextPath() + "/MeGustaController");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else {
            // Edición
            try {
                MeGustaEnPublicacionDAO dao = new MeGustaEnPublicacionDAOimp();
                dao.update(meGusta);
                response.sendRedirect(request.getContextPath() + "/MeGustaController");
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
