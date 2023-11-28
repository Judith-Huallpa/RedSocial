<%-- 
    Document   : mostrarListaChats
    Created on : 27-nov-2023, 17:14:49
    Author     : zerlu
--%>


<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Amigos"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<Amigos> lista = (List<Amigos>) request.getAttribute("amigosAceptados");
    System.out.println(lista);
%>
<!DOCTYPE html>
<html>
    <%@ include file="head.jsp" %>

    <body>
        <%@ include file="nav.jsp" %>

        <div class="container">
            <h1 class="mb-4">Chat de Amigos</h1>

            <c:forEach var="amigo" items="${amigosAceptados}">
                <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">
                            <b>${amigo.user_id1.nombre}</b> chatea con tu amigo <b>${amigo.user_id2.nombre}</b>
                        </h5>

                        <!-- Botón para abrir el chat -->
                        <form action="AbrirChat" method="POST" style="display:inline;">
                            <input type="hidden" name="userId1" value="${amigo.user_id1.user_id}">
                            <input type="hidden" name="userId2" value="${amigo.user_id2.user_id}">
                            <a class="btn btn-primary" type="submit" href="chats.jsp">Abrir Chat</a>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>


        <!-- Otro contenido HTML y scripts necesarios -->

    </body>
</html>