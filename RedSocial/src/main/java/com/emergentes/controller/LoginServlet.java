/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.UsuarioDAOimp;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zerlu
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correoElectronico = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        UsuarioDAOimp usuarioDAO = new UsuarioDAOimp();

        try {
            if (usuarioDAO.validarCredenciales(correoElectronico, contrasena)) {
                // Obtener el objeto Usuario con todos los detalles
                Usuario usuario = obtenerUsuario(correoElectronico);

                HttpSession session = request.getSession();

                // Guardar el objeto Usuario en la sesión
                session.setAttribute("usuario", usuario);

                response.sendRedirect("perfileUsuario.jsp");
            } else {
                // Las credenciales no son válidas, redirige o realiza otras acciones
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }

    private Usuario obtenerUsuario(String correoElectronico) {
        UsuarioDAOimp usuarioDAO = new UsuarioDAOimp();
        try {
            return usuarioDAO.getUsuarioByCorreoElectronico(correoElectronico);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
