<%@page import="com.emergentes.modelo.Grupos"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Grupos> lista = (List<Grupos>) request.getAttribute("grupos");
%>
<!DOCTYPE html>
<html>
    <%@ include file="head.jsp" %>
    <body>
        <%@ include file="nav.jsp" %>

        <div class="container mt-5">
            <!-- Botón flotante para crear un grupo -->
            <button type="button" class="btn btn-primary btn-floating" data-bs-toggle="modal" data-bs-target="#crearGrupoModal">
                Crear Grupo
            </button>

            <!-- Modal para crear un grupo -->
            <div class="modal fade" id="crearGrupoModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Crear Grupo</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <!-- Formulario para crear un grupo -->
                            <form action="GrupoController" method="POST">
                                <div class="mb-3">
                                    <label for="nombreGrupo" class="form-label">Nombre del Grupo:</label>
                                    <input type="text" class="form-control" id="nombreGrupo" name="nombreDelGrupo" required>
                                </div>
                                <div class="mb-3">
                                    <label for="descripcion" class="form-label">Descripción:</label>
                                    <textarea class="form-control" id="descripcion" name="descripcion" rows="3"></textarea>
                                </div>
                                <input type="hidden" name="id" value="0"> <!-- Campo oculto para indicar que es un nuevo grupo -->
                                <input type="hidden" name="idUser" value="<%= usuario.getUser_id()%>"> <!-- Campo oculto para el ID del usuario actual -->
                                <button type="submit" class="btn btn-primary">Crear Grupo</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <h4>GRUPOS</h4>
            <div class="list-group mt-3">

                <c:forEach var="g" items="${grupos}">
                    <div class="list-group-item list-group-item-action">
                        <div class="card mb-3">
                            <div class="card-body">
                                <h5 class="card-title">${g.nombreDelGrupo}</h5>
                                <p class="card-text">${g.descripcion}</p>
                            </div>
                            <div class="card-footer d-flex justify-content-between">
                                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editarGrupoModal${g.group_id}">
                                    Editar
                                </button>
                                <a href="GrupoController?action=delete&id=${g.group_id}" class="btn btn-danger" onclick="return(confirm('Esta seguro de eliminar a ${g.nombreDelGrupo}?'))">
                                    Eliminar
                                </a>
                                <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#agregarMiembrosModal${g.group_id}">
                                    Agregar Miembros <span class="material-symbols-outlined">
                                        group_add
                                    </span>
                                </button>
                            </div>
                        </div>
                    </div>

                    <!-- Modal para editar grupo -->
                    <div class="modal fade" id="editarGrupoModal${g.group_id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Editar Grupo</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <!-- Formulario para editar el grupo -->
                                    <form action="GrupoController" method="POST">
                                        <div class="mb-3">
                                            <label for="nombreGrupo" class="form-label">Nombre del Grupo:</label>
                                            <input type="text" class="form-control" id="nombreGrupo" name="nombreDelGrupo" value="${g.nombreDelGrupo}" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="descripcion" class="form-label">Descripción:</label>
                                            <textarea class="form-control" id="descripcion" name="descripcion" rows="3">${g.descripcion}</textarea>
                                        </div>
                                        <input type="hidden" name="id" value="${g.group_id}">
                                        <input type="hidden" name="idUser" value="<%= usuario.getUser_id()%>">
                                        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal para agregar miembros -->
                    <div class="modal fade" id="agregarMiembrosModal${g.group_id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Agregar Miembros al Grupo ${g.nombreDelGrupo}</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <!-- Formulario para agregar miembros -->
                                    <form action="MiembroGrupoController" method="POST">
                                        <input type="hidden" name="groupId" value="${g.group_id}">
                                        <input type="hidden" name="userId" value="<%= usuario.getUser_id()%>">
                                        <div class="mb-3">
                                            <label for="userId" class="form-label">ID del Usuario:</label>
                                            <input type="text" class="form-control" id="userId" name="userId" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="rolDeUsuarioEnElGrupo" class="form-label">Rol del Usuario en el Grupo:</label>
                                            <input type="text" class="form-control" id="rolDeUsuarioEnElGrupo" name="rolDeUsuarioEnElGrupo" required>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Agregar Miembro</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>