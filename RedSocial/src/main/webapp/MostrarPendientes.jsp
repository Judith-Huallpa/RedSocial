<%-- 
    Document   : MostrarPendientes
    Created on : 27-nov-2023, 16:14:46
    Author     : zerlu
--%>

<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Amigos"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <%@ include file="head.jsp" %>

    <body>
        <%@ include file="nav.jsp" %>

        <div class="container">
            <h1 class="mb-4">Solicitudes de Amistad Pendientes</h1>

            <c:forEach var="amigo" items="${amigosPendientes}">
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">${amigo.user_id1.nombre} te ha enviado una solicitud de amistad ${amigo.user_id2.nombre}</h5>

                        <!-- Otros detalles del amigo que deseas mostrar -->

                        <form action="AmigoController" method="POST" style="display:inline;">
                            <input type="hidden" name="id" value="${amigo.friendship_id}">
                            <input type="hidden" name="usuarioId" value="${amigo.user_id1.user_id}">
                            <input type="hidden" name="amigoId" value="${amigo.user_id2.user_id}">
                            <input type="hidden" name="estado_amistad" value="aceptada">
                            <button class="btn btn-success" type="submit">Aceptar solicitud</button>
                        </form>

                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Otro contenido HTML y scripts necesarios -->

    </body>
</html>