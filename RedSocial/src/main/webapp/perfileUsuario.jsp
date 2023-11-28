<%-- 
    Document   : perfileUsuario
    Created on : 15-nov-2023, 23:54:37
    Author     : zerlu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Publicaciones"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Publicaciones> lista = (List<Publicaciones>) request.getAttribute("publicaciones");

%>
<!DOCTYPE html>
<html>
    <%@ include file="head.jsp" %>
    <body>
        <%@ include file="nav.jsp" %>

        <header class="py-5 bg-image-full" style="background-image: url('https://source.unsplash.com/wfh8dDlNFOk/1600x900')">
            <div class="text-center my-5">
                <img class="img-fluid rounded-circle mb-4" src="https://dummyimage.com/150x150/6c757d/dee2e6.jpg" alt="..." />
                <h1 class="text-white fs-3 fw-bolder"><%= usuario.getNombre()%></h1>
                <p class="text-white-50 mb-0"><%= descripcion%></p>
            </div>
        </header>
        <section class="py-5">
            <div class="container my-5">
                <div class="row justify-content-center">
                    <c:forEach var="publicacion" items="${publicaciones}">
                        <div class="card mb-3 col-md-6 mx-auto">
                            <div class="card-body">
                                <!-- Título con nombre de usuario y grupo -->
                                <h5 class="card-title">
                                    <a href="comentarios.jsp?postId=${publicacion.post_id}&nombreUsuario=${publicacion.user_id.nombre}&nombreGrupo=${publicacion.grupo_id.nombreDelGrupo}">
                                        ${publicacion.user_id.nombre} en ${publicacion.grupo_id.nombreDelGrupo}
                                    </a>

                                </h5>
                                    
                                <!-- Condición para mostrar la foto si existe -->
                                <c:if test="${not empty publicacion.foto_de_publish}">
                                    <img src="${publicacion.foto_de_publish}" alt="Foto de la publicación" class="img-fluid">
                                </c:if>

                                <p class="card-text">${publicacion.contenido_del_mensaje}</p>

                                <!-- Botones debajo del contenido -->
                                <div class="d-flex justify-content-between align-items-center">
                                    <!-- Botón de acordeón para comentarios -->
                                    <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#comentariosCollapse${publicacion.post_id}" aria-expanded="false" aria-controls="comentariosCollapse${publicacion.post_id}">
                                        <span class="material-symbols-outlined">
                                            cognition ${publicacion.cantidadComentario}
                                        </span>
                                    </button>

                                    <!-- Botón de "Me gusta" -->
                                    <form action="MeGustaController" method="POST">
                                        <input type="hidden" name="id" value="0">
                                        <input type="hidden" class="form-control" id="userId" name="userId" value="<%= usuario.getUser_id()%>" readonly required>
                                        <input type="hidden" class="form-control" id="postId" name="postId" value="${publicacion.post_id}">

                                        <button class="btn btn-success" type="submit">
                                            <span class="material-symbols-outlined">
                                                favorite ${publicacion.cantidadLikes}
                                            </span>
                                        </button>
                                    </form>
                                </div>

                                <!-- Acordeón de comentarios -->
                                <div class="collapse mt-2" id="comentariosCollapse${publicacion.post_id}">
                                    <form action="ComentarioController" method="POST">
                                        <input type="hidden" name="id" value="0">
                                        <input type="hidden" name="postId" value="${publicacion.post_id}">
                                        <input type="hidden" class="form-control" id="userId" name="userId" value="<%= usuario.getUser_id()%>" readonly required>

                                        <div class="mb-3">
                                            <label for="contenidoComentario" class="form-label">Comentario:</label>
                                            <textarea class="form-control" id="contenidoComentario" name="contenidoComentario" rows="2" required></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Comentar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
