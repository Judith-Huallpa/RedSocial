/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimp;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zerlu
 */
@WebServlet(name = "BuscarAmigos", urlPatterns = {"/BuscarAmigos"})
public class BuscarAmigos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String terminoBusqueda = request.getParameter("terminoBusqueda");
            UsuarioDAO dao = new UsuarioDAOimp();
            List<Usuario> usuariosEncontrados = dao.buscarUsuarios(terminoBusqueda);

            // Configura la lista como un atributo en el objeto request
            request.setAttribute("listaAmigos", usuariosEncontrados);

            // Redirige a la página JSP deseada
            RequestDispatcher dispatcher = request.getRequestDispatcher("mostrar_amigos.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            System.out.println("Error en la búsqueda de amigos: " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servidor");
        }
    }
}
