/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.MiembrosDeGrupoDAO;
import com.emergentes.dao.MiembrosDeGrupoDAOimp;
import com.emergentes.modelo.Grupos;
import com.emergentes.modelo.Miembros_Grupo;
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
@WebServlet(name = "MiembroGrupoController", urlPatterns = {"/MiembroGrupoController"})
public class MiembroGrupoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MiembrosDeGrupoDAO dao = new MiembrosDeGrupoDAOimp();
            int id = 0;
            Miembros_Grupo miembroGrupo = new Miembros_Grupo();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("miembroGrupo", miembroGrupo);
                    request.getRequestDispatcher("frmMiembrosGrupo.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    miembroGrupo = dao.getById(id);
                    request.setAttribute("miembroGrupo", miembroGrupo);
                    request.getRequestDispatcher("frmMiembrosGrupo.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/MiembroGrupoController");
                    break;
                case "view":
                    List<Miembros_Grupo> lista = dao.getAll();
                    request.setAttribute("miembrosGrupos", lista);
                    request.getRequestDispatcher("miembrosGrupos.jsp").forward(request, response);
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
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String rolDeUsuarioEnElGrupo = request.getParameter("rolDeUsuarioEnElGrupo");
        Miembros_Grupo miembroGrupo = new Miembros_Grupo();

        miembroGrupo.setMembership_id(id);
        
        // Crear objetos Grupos y Usuario con los IDs correspondientes
        Grupos grupo = new Grupos();
        grupo.setGroup_id(groupId);

        Usuario usuario = new Usuario();
        usuario.setUser_id(userId);

        miembroGrupo.setGroup_id(grupo);
        miembroGrupo.setUser_id(usuario);
        
        miembroGrupo.setRol_Usuario_Grupo(rolDeUsuarioEnElGrupo);

        if (id == 0) {
            // Nuevo
            try {
                MiembrosDeGrupoDAO dao = new MiembrosDeGrupoDAOimp();
                dao.insert(miembroGrupo);
                response.sendRedirect(request.getContextPath() + "/MiembroGrupoController");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        } else {
            // Edición
            try {
                MiembrosDeGrupoDAO dao = new MiembrosDeGrupoDAOimp();
                dao.update(miembroGrupo);
                response.sendRedirect(request.getContextPath() + "/MiembroGrupoController");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
