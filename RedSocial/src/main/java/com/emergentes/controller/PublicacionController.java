/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.ComentarioDAO;
import com.emergentes.dao.ComentarioDAOimp;
import com.emergentes.dao.PublicacionDAO;
import com.emergentes.dao.PublicacionDAOimp;
import com.emergentes.modelo.Comentarios;
import com.emergentes.modelo.Grupos;
import com.emergentes.modelo.Publicaciones;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "PublicacionController", urlPatterns = {"/PublicacionController"})
public class PublicacionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PublicacionDAO dao = new PublicacionDAOimp();
            
            int id = 0;
            Publicaciones publicacion = new Publicaciones();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("publicacion", publicacion);
                    request.getRequestDispatcher("publicaciones.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    publicacion = dao.getById(id);
                    request.setAttribute("publicacion", publicacion);
                    request.getRequestDispatcher("publicaciones.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/PublicacionController");
                    break;
                case "view":
                    List<Publicaciones> lista = dao.getAll();
                    request.setAttribute("publicaciones", lista);
                    request.getRequestDispatcher("publicaciones.jsp").forward(request, response);
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
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        String contenidoDelMensaje = request.getParameter("contenidoDelMensaje");
        String fotoDePublicacion = request.getParameter("fotoDePublicacion");
        Publicaciones publicacion = new Publicaciones();

        publicacion.setPost_id(id);
        publicacion.setContenido_del_mensaje(contenidoDelMensaje);
        publicacion.setFoto_de_publish(fotoDePublicacion);
        // Crear un objeto Usuario y establecer el ID
        Usuario usuario = new Usuario();
        usuario.setUser_id(userId);
        publicacion.setUser_id(usuario);

        // Crear un objeto Grupos y establecer el ID
        Grupos grupo = new Grupos();
        grupo.setGroup_id(groupId);
        publicacion.setGrupo_id(grupo);

        if (id == 0) {
            // Nuevo
            try {
                PublicacionDAO dao = new PublicacionDAOimp();
                dao.insert(publicacion);
                response.sendRedirect(request.getContextPath() + "/PublicacionController");
            } catch (Exception ex) {
                System.out.println("Error: en agregar" + ex.getMessage());
                ex.printStackTrace();
                System.out.println(id + "id" + userId + "user grupo" + groupId);
            }
        } else {
            // Edición
            try {
                PublicacionDAO dao = new PublicacionDAOimp();
                dao.update(publicacion);
                response.sendRedirect(request.getContextPath() + "/PublicacionController");
            } catch (Exception ex) {
                System.out.println("Error: en editar" + ex.getMessage());
            }
        }
    }
}
