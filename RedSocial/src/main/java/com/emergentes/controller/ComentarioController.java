/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.ComentarioDAO;
import com.emergentes.dao.ComentarioDAOimp;
import com.emergentes.modelo.Comentarios;
import com.emergentes.modelo.Publicaciones;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zerlu
 */
@WebServlet(name = "ComentarioController", urlPatterns = {"/ComentarioController"})
public class ComentarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ComentarioDAO dao = new ComentarioDAOimp();
            int id;
            Comentarios comentario;
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    comentario = new Comentarios();
                    request.setAttribute("comentario", comentario);
                    request.getRequestDispatcher("publicaciones.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    comentario = dao.getById(id);
                    request.setAttribute("comentario", comentario);
                    request.getRequestDispatcher("publicaciones.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/ComentarioController");
                    break;
                case "view":
                    
                    // También puedes cargar todos los comentarios, si es necesario
                    List<Comentarios> lista = dao.getAll();
                    request.setAttribute("Comentarios", lista);
                    request.getRequestDispatcher("comentarios.jsp").forward(request, response);
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
        String contenidoComentario = request.getParameter("contenidoComentario");
        
        Comentarios comentario = new Comentarios();
        comentario.setCommentId(id);
        comentario.setContenido_del_comentario(contenidoComentario);
        // Crear un objeto Publicaciones y establecer el ID
        Publicaciones publicacion = new Publicaciones();
        publicacion.setPost_id(postId);
        comentario.setPost_id(publicacion);

        Usuario usuario = new Usuario();
        usuario.setUser_id(userId);
        comentario.setUser_id(usuario);
        if (id == 0) {
           
            ComentarioDAO dao = new ComentarioDAOimp();
            try {
                dao.insert(comentario);
                response.sendRedirect(request.getContextPath() + "/PublicacionController");
            } catch (Exception ex) {
                Logger.getLogger(ComentarioController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        } else {
            // Edición
            try {
                ComentarioDAO dao = new ComentarioDAOimp();
                dao.update(comentario);
                response.sendRedirect(request.getContextPath() + "/ComentarioController");
            } catch (Exception ex) {
                System.out.println("Error: en editar" + ex.getMessage());
            }
        }
    }

}
