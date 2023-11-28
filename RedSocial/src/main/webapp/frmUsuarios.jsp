<%@page import="com.emergentes.modelo.Usuario"%>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Registrarme</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#">Dunder Miflin</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="UsuarioController">Iniciar Sesion</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8">
            <h2 class="mb-4">Registrarme</h2>
            <form action="UsuarioController" method="POST">
                <input type="hidden" name="id" value="${usuario.user_id}">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="${usuario.nombre}" required>
                </div>
                <div class="mb-3">
                    <label for="correo" class="form-label">Correo Electrónico:</label>
                    <input type="email" class="form-control" id="correo" name="correoElectronico" value="${usuario.correo_electronico}" required>
                </div>
                <div class="mb-3">
                    <label for="contrasena" class="form-label">Contraseña:</label>
                    <input type="password" class="form-control" id="contrasena" name="contrasena" value="${usuario.contrasena}" required>
                </div>
                
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </form>
        </div>
        <div class="col-md-4">
            <img src="https://www.hollywoodreporter.com/wp-content/uploads/2015/08/hooli_xyz_site_grab.jpg" alt="Descripción de la imagen" class="img-fluid">
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
