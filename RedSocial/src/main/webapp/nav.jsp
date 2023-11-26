<%@page import="com.emergentes.dao.PerfilUsuarioDAOimp"%>
<%@page import="com.emergentes.dao.PerfilUsuarioDAO"%>
<%@page import="com.emergentes.modelo.Usuario"%>
<%@page import="com.emergentes.modelo.Perfil_Usuario"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%
    
    String nombreUsuario = (String) session.getAttribute("nombreUsuario");
    Object idUsuarioObj = session.getAttribute("idUsuario");
    Perfil_Usuario perfil = new Perfil_Usuario();
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    session.setAttribute("usuario", usuario);

    PerfilUsuarioDAO perfilUsuarioDAO = new PerfilUsuarioDAOimp();
    String descripcion = perfilUsuarioDAO.getById(usuario.getUser_id()).getDescripcion();
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="UsuarioController">Dunder Miflin</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="PublicacionController">Que hay de Nuevo</a></li>
                <li class="nav-item"><a class="nav-link" href=""><span class="material-symbols-outlined" style="color: #fff">
                            notifications
                        </span></a></li>
                <li class="nav-item"><a class="nav-link" href="#!"><span class="material-symbols-outlined">
                            chat
                        </span></a></li>

                <li class="nav-item"><a class="nav-link" href="GrupoController"><span class="material-symbols-outlined">
                            groups
                        </span></a></li>
                <li class="nav-item"><a class="nav-link" href="PerfilUsuarioController?action=edit&id=<%= usuario.getUser_id()%>"><span class="material-symbols-outlined">
                            manufacturing
                        </span></a></li>
                <i class="fas fa-camera"></i> <!-- Icono de cámara -->
                <i class="fas fa-user"></i>   <!-- Icono de usuario -->
            </ul>
        </div>
    </div>
</nav>
                            <p>${error}</p>