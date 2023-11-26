/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.GrupoDAO;
import com.emergentes.dao.GrupoDAOimp;
import com.emergentes.modelo.Grupos;
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
@WebServlet(name = "GrupoController", urlPatterns = {"/GrupoController"})
public class GrupoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GrupoDAO dao = new GrupoDAOimp();
            int id = 0;
            Grupos grupo = new Grupos();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("grupo", grupo);
                    request.getRequestDispatcher("grupos.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    grupo = dao.getById(id);
                    request.setAttribute("grupo", grupo);
                    request.getRequestDispatcher("grupos.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/GrupoController");
                    break;
                case "view":
                    List<Grupos> lista = dao.getAll();
                    request.setAttribute("grupos", lista);
                    request.getRequestDispatcher("grupos.jsp").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("grupos.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Imprimir la traza completa del error en la consola
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("nav.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        String nombreDelGrupo = request.getParameter("nombreDelGrupo");
        String descripcion = request.getParameter("descripcion");
        Grupos grupo = new Grupos();

        grupo.setGroup_id(id);
        grupo.setNombreDelGrupo(nombreDelGrupo);
        grupo.setDescripcion(descripcion);
        Usuario usuario = new Usuario();
        usuario.setUser_id(idUser);

        grupo.setUser_id(usuario);

        try {
            GrupoDAO dao = new GrupoDAOimp();
            if (id == 0) {
                // Nuevo
                dao.insert(grupo);
            } else {
                // Edición
                dao.update(grupo);
            }
            response.sendRedirect(request.getContextPath() + "/GrupoController");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // Puedes agregar mensajes de error o redirigir a una página de error
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
