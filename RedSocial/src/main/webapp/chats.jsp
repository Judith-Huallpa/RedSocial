<%-- 
    Document   : chats
    Created on : 27-nov-2023, 17:39:29
    Author     : zerlu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chat</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link href="css/styles.css" rel="stylesheet" />
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                margin: 0;
            }

            .chat-container {
                max-width: 600px;
                margin: 50px auto;
                border: 1px solid #ccc;
                border-radius: 8px;
                overflow: hidden;
            }

            .chat-header {
                background-color: #4CAF50;
                color: white;
                padding: 15px;
                text-align: center;
            }

            .chat-messages {
                padding: 15px;
                overflow-y: auto;
                max-height: 300px;
            }

            .message {
                margin-bottom: 10px;
            }

            .message .user {
                font-weight: bold;
                color: #007bff;
            }

            .message .timestamp {
                font-size: 0.8em;
                color: #6c757d;
                margin-left: 10px;
            }

            .chat-input {
                padding: 15px;
                background-color: #f1f1f1;
                border-top: 1px solid #ccc;
            }

            .input-group {
                display: flex;
            }

            .input-group input {
                flex: 1;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                margin-right: 10px;
            }

            .input-group button {
                padding: 10px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
        </style>
    </head>

    <body>
        <%@ include file="nav.jsp" %>
        <div class="container">
            <div class="chat-container">
                <div class="chat-header">
                    Chat con Amigo
                </div>
                <div class="chat-messages" id="chatMessages">
                    <!-- Aquí se mostrarán los mensajes -->
                    <div class="message">
                        <span class="user">Amigo1:</span>
                        <span class="timestamp">10:00 AM</span>
                        <p>Hola, ¿cómo estás?</p>
                    </div>
                    <div class="message">
                        <span class="user">Tú:</span>
                        <span class="timestamp">10:05 AM</span>
                        <p>Hola, estoy bien. ¿Y tú?</p>
                    </div>
                </div>
                <div class="chat-input">
                    <div class="input-group">
                        <input type="text" class="form-control" id="messageInput" placeholder="Escribe tu mensaje...">
                        <button class="btn btn-primary" onclick="sendMessage()">Enviar</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                            function sendMessage() {
                                var messageInput = document.getElementById('messageInput');
                                var chatMessages = document.getElementById('chatMessages');

                                // Obtener el contenido del mensaje
                                var messageText = messageInput.value;

                                // Crear un nuevo elemento de mensaje
                                var messageElement = document.createElement('div');
                                messageElement.className = 'message';
                                messageElement.innerHTML = '<span class="user">Tú:</span>' +
                                        '<span class="timestamp">' + getCurrentTime() + '</span>' +
                                        '<p>' + messageText + '</p>';

                                // Agregar el nuevo mensaje al contenedor de mensajes
                                chatMessages.appendChild(messageElement);

                                // Limpiar el cuadro de entrada de mensajes
                                messageInput.value = '';
                            }

                            function getCurrentTime() {
                                var now = new Date();
                                var hours = now.getHours();
                                var minutes = now.getMinutes();

                                // Formatear la hora como hh:mm
                                return (hours < 10 ? '0' : '') + hours + ':' + (minutes < 10 ? '0' : '') + minutes;
                            }
        </script>
    </body>

</html>

