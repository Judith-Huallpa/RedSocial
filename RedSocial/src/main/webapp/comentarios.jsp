<%@page import="com.emergentes.dao.PublicacionDAOimp"%>
<%@page import="com.emergentes.dao.PublicacionDAO"%>
<%@ page import="com.emergentes.dao.UsuarioDAOimp" %>
<%@ page import="com.emergentes.dao.UsuarioDAO" %>
<%@ page import="com.emergentes.dao.ComentarioDAOimp" %>
<%@ page import="com.emergentes.dao.ComentarioDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.emergentes.modelo.Comentarios" %>
<%@ page import="com.emergentes.modelo.Publicaciones" %>
<%@ page import="com.emergentes.modelo.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <%@ include file="head.jsp" %>
        <style>
            /* Agrega estilos personalizados aquí */
            .comment-card {
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <%@ include file="nav.jsp" %>
        <div class="container mt-4">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <%-- Contenido de la publicación y foto --%>
                    <%                        List<Comentarios> lista = (List<Comentarios>) request.getAttribute("Comentarios");

                        // Obtener el valor de postId de la URL usando EL
                        String postIdParam = request.getParameter("postId");
                        String nombrePost = request.getParameter("nombreUsuario");
                        String nombreGrupo = request.getParameter("nombreGrupo");
                        System.out.println(postIdParam);
                        // Verificar si el postIdParam no es nulo ni vacío antes de convertirlo a un número
                        int postId = (postIdParam != null && !postIdParam.isEmpty()) ? Integer.parseInt(postIdParam) : 0;

                        // Lógica para obtener y mostrar información de la publicación
                        PublicacionDAO publicacionDAO = new PublicacionDAOimp();
                        Publicaciones publicacion = publicacionDAO.getById(postId);
                        //System.out.println(publicacion);

                        String imagen = publicacion.getFoto_de_publish();
                        //System.out.println(imagen);
  
%>

                    <div class="card mb-4">
                        <div class="card-body">
                            <h5 class="card-title"><%= nombrePost%> en <%= nombreGrupo%></h5>
                            <p class="card-text"><%= publicacion.getContenido_del_mensaje()%></p>
                            <%-- Condición para mostrar la foto si existe --%>
                            <c:if test="${not empty publicacion.foto_de_publish or imagen eq null}">
                                <img src="<%=imagen%>" alt="Foto de la publicación" class="img-fluid">
                            </c:if>

                        </div>
                    </div>

                    <hr>

                    <%-- Comentarios --%>
                    <h5>Comentarios</h5>

                    <%
                        ComentarioDAO com = new ComentarioDAOimp();
                        List<Comentarios> comentariosList = com.getByPostId(postId);

                        // Verificar si hay comentarios
                        if (!comentariosList.isEmpty()) {
                            for (Comentarios comentario : comentariosList) {
                                UsuarioDAO usu = new UsuarioDAOimp();
                                usuario = usu.getById(comentario.getUser_id().getUser_id());

                                if (usuario != null) {
                    %>
                    <div class="card mt-2 comment-card">
                        <div class="card-body">
                            <p class="mb-1"><strong><%= usuario.getNombre()%></strong>: <%= comentario.getContenido_del_comentario()%></p>
                        </div>
                    </div>
                    <%
                            } else {
                                System.out.println("Usuario no encontrado para id " + comentario.getUser_id().getUser_id());
                            }
                        }
                    } else {
                    %>
                    <div class="alert alert-info mt-3" role="alert">
                        No hay comentarios aún.
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>

        <c:forEach var="comentario" items="${comentarios}">
            <div class="card mt-2 comment-card">
                <div class="card-body">
                    <p class="mb-1"><strong>${comentario.user_id.nombre}</strong>: ${comentario.contenido_del_comentario}</p>
                </div>
            </div>
        </c:forEach>
        
        <!-- Otro código de la página... -->


        <!-- Importa los scripts de Bootstrap al final del cuerpo para un mejor rendimiento -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
