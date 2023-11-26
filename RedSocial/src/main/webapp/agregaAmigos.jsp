<%-- 
    Document   : agregaAmigos
    Created on : 26-nov-2023, 16:39:26
    Author     : zerlu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <title>Agregar Amigos</title>
</head>
<body>

<div class="container mt-5">
  <h2 class="mb-4">Agregar Amigos</h2>

  <!-- Formulario para agregar amigos -->
  <form>
    <div class="form-group">
      <label for="nombreAmigo">Nombre del Amigo:</label>
      <input type="text" class="form-control" id="nombreAmigo" placeholder="Ingrese el nombre del amigo" required>
    </div>
    <button type="submit" class="btn btn-primary">Agregar Amigo</button>
  </form>

  <hr>

  <!-- Campo de búsqueda de amigos -->
  <h3 class="mb-3">Buscar Amigos</h3>
  <div class="input-group mb-3">
    <input type="text" class="form-control" placeholder="Buscar amigos" aria-label="Buscar amigos" aria-describedby="button-addon2">
    <div class="input-group-append">
      <button class="btn btn-outline-secondary" type="button" id="button-addon2">Buscar</button>
    </div>
  </div>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
