<%-- 
    Document   : mostrar_amigos
    Created on : 26-nov-2023, 23:38:51
    Author     : zerlu
--%>

<%@page import="com.emergentes.modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Usuario> lista = (List<Usuario>) request.getAttribute("listaAmigos");
%>

<!DOCTYPE html>
<html>
    <%@ include file="head.jsp" %>
    <body>
        <%@ include file="nav.jsp" %>
        <div id="resultadosBusqueda" class="container mt-5">
            <h2>Lista de Amigos</h2>
            <ul class="list-group mt-3">
                <!-- Itera sobre la lista de amigos -->
                <c:forEach var="amigo" items="${listaAmigos}">
                    <c:if test="${amigo.user_id ne usuario.getUser_id()}">
                        <li class="list-group-item">
                            <div class="d-flex justify-content-between align-items-center">
                                <div>
                                    <h5>${amigo.nombre}</h5>
                                    <p>Correo Electrónico: ${amigo.correo_electronico}</p>
                                </div>

                                <form action="AmigoController" method="POST">
                                    <input type="hidden" name="id" value="0">
                                    <input type="hidden" class="form-control" id="usuarioId" name="usuarioId" value="<%= usuario.getUser_id()%>" readonly required>
                                    <input type="hidden" class="form-control" id="amigoId" name="amigoId" value="${amigo.user_id}">
                                    <input type="hidden" class="form-control" id="estado_amistad" name="estado_amistad" value="pendiente">
                                    <button class="btn btn-success" type="submit">
                                        <span class="material-symbols-outlined">
                                            person_add 
                                        </span>Agregar Amigo
                                    </button>
                                </form>
                            </div>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
