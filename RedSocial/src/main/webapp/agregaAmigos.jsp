<%-- 
    Document   : agregaAmigos
    Created on : 26-nov-2023, 16:39:26
    Author     : zerlu
--%>

<%@page import="com.emergentes.dao.AmigoDAOimp"%>
<%@page import="com.emergentes.dao.AmigoDAO"%>
<%@page import="com.emergentes.modelo.Amigos"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="es">
    <%@ include file="head.jsp" %>
    <body>
        <%@ include file="nav.jsp" %>
        <div class="container mt-5">
            <!-- Formulario de búsqueda de amigos -->
            <form id="formBusqueda" action="BuscarAmigos" method="POST">
                <h3 class="mb-3">Buscar Amigos</h3>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" id="terminoBusqueda" name="terminoBusqueda" placeholder="Buscar amigos" aria-label="Buscar amigos" aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="submit">Buscar</button>
                    </div>
                </div>
            </form>


            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <form action="mostrarPendientesAmigos" method="POST" class="mb-3">
                            <input type="hidden" class="form-control" id="userId" name="userId" value="<%= usuario.getUser_id()%>" readonly required>
                            
                            <button class="btn btn-success" type="submit"><span class="material-symbols-outlined">
                                maps_ugc
                            </span> Ver Solicitudes de Amistad</button>
                        </form>
                    </div>
                    <div class="col-md-6">
                        <form action="AmigosChat" method="POST" class="mb-3">
                            <input type="hidden" class="form-control" id="userId" name="userId" value="<%= usuario.getUser_id()%>" readonly required>
                           
                            <button class="btn btn-success" type="submit"> <span class="material-symbols-outlined">
                                forum
                            </span> Ver Chats</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
