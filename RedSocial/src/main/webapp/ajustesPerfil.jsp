<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="head.jsp" %>
    </head>
    <body>
        <%@ include file="nav.jsp" %>

        <div class="container mt-5">
            <div class="row">
                <div class="col-md-8">
                    <h2>Editar Perfil</h2>
                    <form action="PerfilUsuarioController" method="POST">
                        <input type="hidden" name="id" value="${perfilUsuario != null ? perfilUsuario.profile_Id : ''}">
                        <input type="hidden" name="userId" value="${perfilUsuario != null && perfilUsuario.user_id != null ? perfilUsuario.user_id.user_id : ''}">  
                        <input type="hidden" name="fotoPerfil" value="">
                        <div class="form-group">
                            <label for="descripcion">Descripción:</label>
                            <textarea class="form-control" id="descripcion" name="descripcion" required><c:out value="${perfilUsuario.descripcion}" /></textarea>
                        </div>

                        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                    </form>
                    <!-- Botón para abrir el modal -->


                    <!-- Modal para agregar la imagen -->
                    <div class="modal fade" id="imagenModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Agregar Imagen</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <!-- Formulario para agregar la imagen -->
                                    <form action="PerfilUsuarioController" method="POST" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label for="fotoPerfil">Seleccionar Imagen:</label>
                                            <input type="file" class="form-control" id="fotoPerfil" name="fotoPerfil" accept="image/*" onchange="mostrarImagen()">
                                        </div>
                                        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <img id="imagenSeleccionada" src="https://www.hollywoodreporter.com/wp-content/uploads/2015/08/hooli_xyz_site_grab.jpg" alt="Descripción de la imagen" class="img-fluid">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#imagenModal">
                        Agregar Imagen
                    </button>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <script>
                                                function mostrarImagen() {
                                                    var input = document.getElementById('fotoPerfil');
                                                    var imagen = document.getElementById('imagenSeleccionada');

                                                    if (input.files && input.files[0]) {
                                                        var reader = new FileReader();

                                                        reader.onload = function (e) {
                                                            imagen.src = e.target.result;
                                                        };

                                                        reader.readAsDataURL(input.files[0]);
                                                    }
                                                }
        </script>
    </body>

</html>
