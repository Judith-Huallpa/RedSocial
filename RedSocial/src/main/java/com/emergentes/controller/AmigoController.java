/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controller;

import com.emergentes.dao.AmigoDAO;
import com.emergentes.dao.AmigoDAOimp;
import com.emergentes.modelo.Amigos;
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
@WebServlet(name = "AmigoController", urlPatterns = {"/AmigoController"})
public class AmigoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AmigoDAO dao = new AmigoDAOimp();
            int id;
            Amigos amigo;
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    amigo = new Amigos();
                    request.setAttribute("amigo", amigo);
                    request.getRequestDispatcher("amigos.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    amigo = dao.getById(id);
                    request.setAttribute("amigo", amigo);
                    request.getRequestDispatcher("amigos.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath() + "/AmigoController");
                    break;
                case "view":
                    
                    List<Amigos> lista = dao.getAll();
                    request.setAttribute("amigos", lista);
                    request.getRequestDispatcher("agregaAmigos.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servidor");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
            int amigoId = Integer.parseInt(request.getParameter("amigoId"));
            String estadoAmistad = request.getParameter("estado_amistad");

            Amigos amigo = new Amigos();
            amigo.setFriendship_id(id);

            // Crear objetos Usuario y establecer los IDs
            Usuario usuario1 = new Usuario();
            usuario1.setUser_id(usuarioId);
            amigo.setUser_id1(usuario1);

            Usuario usuario2 = new Usuario();
            usuario2.setUser_id(amigoId);
            amigo.setUser_id2(usuario2);

            amigo.setEstado_amistad(estadoAmistad);

            AmigoDAO dao = new AmigoDAOimp();

            if (id == 0) {
                dao.insert(amigo);
            } else {
                dao.update(amigo);
            }

            response.sendRedirect(request.getContextPath() + "/AmigoController");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parámetros no válidos");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servidor");
        }
    }

}
