/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.PerfilUsuarioDAO;
import com.emergentes.dao.PerfilUsuarioDAOimp;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimp;
import com.emergentes.modelo.Perfil_Usuario;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author zerlu
 */
@WebServlet(name = "PerfilUsuarioController", urlPatterns = {"/PerfilUsuarioController"})
public class PerfilUsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PerfilUsuarioDAO dao = new PerfilUsuarioDAOimp();
            int id = 0;
            Perfil_Usuario perfilUsuario = new Perfil_Usuario();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("perfilUsuario", perfilUsuario);
                    request.getRequestDispatcher("ajustesPerfil.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    perfilUsuario = dao.getById(id);
                    request.setAttribute("perfilUsuario", perfilUsuario);
                    request.getRequestDispatcher("ajustesPerfil.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/PerfilUsuarioController");
                    break;
                case "view":
                    List<Perfil_Usuario> lista = dao.getAll();
                    request.setAttribute("perfilesUsuarios", lista);
                    request.getRequestDispatcher("perfileUsuario.jsp").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("perfileUsuario.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        String descripcion = request.getParameter("descripcion");
        
        Perfil_Usuario perfilUsuario = new Perfil_Usuario();
        // Crear un objeto Usuario y establecer el user_id
        Usuario usuario = new Usuario();
        usuario.setUser_id(userId);

        perfilUsuario.setProfile_Id(id);
        perfilUsuario.setUser_id(usuario);
        perfilUsuario.setDescripcion(descripcion);

        // Invocar el método insert o update del DAO según sea necesario
        PerfilUsuarioDAO perfilUsuarioDAO = new PerfilUsuarioDAOimp();

        if (id == 0) {
            try {
                // Nuevo
                perfilUsuarioDAO.insert(perfilUsuario);
            } catch (Exception ex) {
                Logger.getLogger(PerfilUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                // Edición
                perfilUsuarioDAO.update(perfilUsuario);
            } catch (Exception ex) {
                Logger.getLogger(PerfilUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
