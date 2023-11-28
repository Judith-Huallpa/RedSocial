/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.AmigoDAO;
import com.emergentes.dao.AmigoDAOimp;
import com.emergentes.modelo.Amigos;
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
@WebServlet(name = "mostrarPendientesAmigos", urlPatterns = {"/mostrarPendientesAmigos"})
public class mostrarPendientesAmigos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            AmigoDAO amigoDAO = new AmigoDAOimp();
            
            // Obtener la lista de amigos pendientes
            List<Amigos> amigosPendientes = amigoDAO.getAmigosConSolicitudesPendientes(userId);

            // Guardar la lista en el objeto request
            request.setAttribute("amigosPendientes", amigosPendientes);

            // Redirigir a tu vista JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("MostrarPendientes.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            System.out.println("Error al encontrar pendientes: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servidor");
        }
    }
}
