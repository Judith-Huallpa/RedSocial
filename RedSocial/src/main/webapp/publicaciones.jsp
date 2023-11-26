<%@page import="com.emergentes.modelo.Comentarios"%>
<%@page import="com.emergentes.modelo.Me_Gusta"%>
<%@page import="com.emergentes.dao.PublicacionDAOimp"%>
<%@page import="com.emergentes.dao.PublicacionDAO"%>
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
        <div class="container mt-5">
            <!-- Botón para crear nueva publicación -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#crearPublicacionModal">
                Nueva Publicación
            </button>

            <!-- Modal para crear una nueva publicación -->
            <div class="modal fade" id="crearPublicacionModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Nueva Publicación</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Formulario para crear una nueva publicación -->
                            <form action="PublicacionController" method="POST">
                                <input type="hidden" name="id" value="0">
                                <input type="hidden" class="form-control" id="userId" name="userId" value="<%= usuario.getUser_id()%>" readonly required>
                                <input type="hidden" class="form-control" id="groupId" name="groupId" value="1">

                                <div class="mb-3">
                                    <label for="contenidoDelMensaje" class="form-label">Contenido de la Publicación:</label>
                                    <textarea class="form-control" id="contenidoDelMensaje" name="contenidoDelMensaje" rows="3" required></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="fotoDePublicacion" class="form-label">URL de la Foto:</label>
                                    <input type="text" class="form-control" id="fotoDePublicacion" name="fotoDePublicacion">
                                </div>
                                <button type="submit" class="btn btn-primary">Publicar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Sección para mostrar las publicaciones existentes -->
            <div class="mt-3">
                <!-- Aquí puedes usar un bucle para mostrar las publicaciones -->
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
                <!-- Fin del bucle -->
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>

    </body>
</html>
