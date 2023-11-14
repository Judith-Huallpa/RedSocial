/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.GrupoDAO;
import com.emergentes.dao.GrupoDAOimp;
import com.emergentes.modelo.Grupos;
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
                    request.getRequestDispatcher("frmGrupos.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    grupo = dao.getById(id);
                    request.setAttribute("grupo", grupo);
                    request.getRequestDispatcher("frmGrupos.jsp").forward(request, response);
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
        String nombreDelGrupo = request.getParameter("nombreDelGrupo");
        String descripcion = request.getParameter("descripcion");
        Grupos grupo = new Grupos();

        grupo.setGroup_id(id);
        grupo.setNombreDelGrupo(nombreDelGrupo);
        grupo.setDescripcion(descripcion);

        if (id == 0) {
            // Nuevo
            try {
                GrupoDAO dao = new GrupoDAOimp();
                dao.insert(grupo);
                response.sendRedirect(request.getContextPath() + "/GrupoController");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else {
            // Edición
            try {
                GrupoDAO dao = new GrupoDAOimp();
                dao.update(grupo);
                response.sendRedirect(request.getContextPath() + "/GrupoController");
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
