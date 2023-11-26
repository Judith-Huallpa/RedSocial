<%-- 
    Document   : perfileUsuario
    Created on : 15-nov-2023, 23:54:37
    Author     : zerlu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                    <div class="col-lg-6">
                        as
                        dfasdfasdf
                    </div>

                </div>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
