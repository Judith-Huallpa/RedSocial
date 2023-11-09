CREATE DATABASE red_social;
USE red_social;

-- Tabla de Usuarios
CREATE TABLE Usuarios (
    user_id INT PRIMARY KEY,
    nombre VARCHAR(255),
    correo_electronico VARCHAR(255),
    contrasena VARCHAR(255),
    fecha_registro DATE
);

-- Tabla de Perfiles de Usuario
CREATE TABLE Perfiles_Usuario (
    profile_id INT PRIMARY KEY,
    user_id INT,
    foto_de_perfil VARCHAR(255),
    descripcion TEXT,
    -- Puedes agregar campos para las redes sociales aquí
    FOREIGN KEY (user_id) REFERENCES Usuarios(user_id)
);

-- Tabla de Grupos
CREATE TABLE Grupos (
    group_id INT PRIMARY KEY,
    nombre_del_grupo VARCHAR(255),
    descripcion TEXT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES Usuarios(user_id)
);
-- Tabla de Publicaciones
CREATE TABLE Publicaciones (
    post_id INT PRIMARY KEY,
    user_id INT,
    grupo_id INT,
    contenido_del_mensaje TEXT,
   foto_de_PUBLISH VARCHAR(255),
    fecha_publicacion DATETIME,
    FOREIGN KEY (grupo_id) REFERENCES Grupos(group_id),
    FOREIGN KEY (user_id) REFERENCES Usuarios(user_id)
);


-- Tabla de Comentarios
CREATE TABLE Comentarios (
    comment_id INT PRIMARY KEY,
    post_id INT,
    user_id INT,
    contenido_del_comentario TEXT,
    fecha_comentario DATETIME,
    FOREIGN KEY (post_id) REFERENCES Publicaciones(post_id),
    FOREIGN KEY (user_id) REFERENCES Usuarios(user_id)
);

-- Tabla de Me gusta en Publicaciones
CREATE TABLE Me_Gusta_En_Publicaciones (
    like_id INT PRIMARY KEY,
    post_id INT,
    user_id INT,
    FOREIGN KEY (post_id) REFERENCES Publicaciones(post_id),
    FOREIGN KEY (user_id) REFERENCES Usuarios(user_id)
);

-- Tabla de Amigos
CREATE TABLE Amigos (
    friendship_id INT PRIMARY KEY,
    user_id1 INT,
    user_id2 INT,
    estado_amistad VARCHAR(20),
    FOREIGN KEY (user_id1) REFERENCES Usuarios(user_id),
    FOREIGN KEY (user_id2) REFERENCES Usuarios(user_id)
);

-- Tabla de Mensajes Privados
CREATE TABLE Mensajes_Privados (
    message_id INT PRIMARY KEY,
    user_id_from INT,
    user_id_to INT,
    contenido_del_mensaje TEXT,
    fecha_envio DATETIME,
    FOREIGN KEY (user_id_from) REFERENCES Usuarios(user_id),
    FOREIGN KEY (user_id_to) REFERENCES Usuarios(user_id)
);

-- Tabla de Notificaciones
CREATE TABLE Notificaciones (
    notification_id INT PRIMARY KEY,
    user_id INT,
    tipo_de_notificacion VARCHAR(20),
    contenido_de_notificacion TEXT,
    fecha_notificacion DATETIME,
    FOREIGN KEY (user_id) REFERENCES Usuarios(user_id)
);


-- Tabla de Miembros de Grupos
CREATE TABLE Miembros_de_Grupos (
    membership_id INT PRIMARY KEY,
    group_id INT,
    user_id INT,
    rol_de_usuario_en_el_grupo VARCHAR(20),
    FOREIGN KEY (group_id) REFERENCES Grupos(group_id),
    FOREIGN KEY (user_id) REFERENCES Usuarios(user_id)
);