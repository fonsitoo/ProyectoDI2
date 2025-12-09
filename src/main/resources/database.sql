DROP DATABASE IF EXISTS marketplacesteam_db;
CREATE DATABASE IF NOT EXISTS marketplacesteam_db;
USE marketplacesteam_db;

CREATE TABLE usuario (
                         id_usuario INT AUTO_INCREMENT PRIMARY KEY,
                         nickname VARCHAR(50) NOT NULL UNIQUE,
                         password VARCHAR(50) NOT NULL,
                         email VARCHAR(100),
                         saldo DECIMAL(10, 2) DEFAULT 0.00,
                         es_admin BOOLEAN DEFAULT FALSE
);

CREATE TABLE juego (
                       id_juego INT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(100) NOT NULL,
                       icono_url VARCHAR(255)
);

CREATE TABLE item (
                      id_item INT AUTO_INCREMENT PRIMARY KEY,
                      nombre VARCHAR(100) NOT NULL,
                      descripcion TEXT,
                      precio DECIMAL(10, 2) NOT NULL,
                      float_value DECIMAL(5, 4),
                      fecha_publicacion DATE,
                      id_usuario INT,
                      id_juego INT,
                      FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
                      FOREIGN KEY (id_juego) REFERENCES juego(id_juego) ON DELETE CASCADE
);

CREATE TABLE categoria (
                           id_categoria INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(50) NOT NULL
);

CREATE TABLE item_categoria (
                                id_item INT,
                                id_categoria INT,
                                PRIMARY KEY (id_item, id_categoria),
                                FOREIGN KEY (id_item) REFERENCES item(id_item) ON DELETE CASCADE,
                                FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria) ON DELETE CASCADE
);

-- INSERTS
INSERT INTO usuario (nickname, password, email, saldo, es_admin) VALUES
                                                                     ('GabeN', 'admin123', 'gaben@steam.com', 9999.99, TRUE),
                                                                     ('TraderPro', '1234', 'trader@gmail.com', 50.00, FALSE),
                                                                     ('NoobPlayer', '0000', 'noob@hotmail.com', 5.00, FALSE);

INSERT INTO juego (nombre) VALUES
                               ('Counter-Strike 2'),
                               ('Team Fortress 2'),
                               ('Dota 2');

INSERT INTO categoria (nombre) VALUES
                                   ('Cuchillo'), ('Rifle'), ('StatTrak™'), ('Cosmético'), ('Inusual');

INSERT INTO item (nombre, descripcion, precio, float_value, fecha_publicacion, id_usuario, id_juego) VALUES
                                                                                                         ('AWP | Dragon Lore', 'La Skin mas conocida', 1500.00, 0.0150, '2023-10-01', 2, 1),
                                                                                                         ('Butterfly Knife | Fade', 'El cuchillo mas vistoso', 800.00, 0.0300, '2023-11-12', 2, 1),
                                                                                                         ('Sartén Dorada', 'Cuerpo a cuerpo legendario', 2500.00, 0.5000, '2023-12-01', 3, 2);

INSERT INTO item_categoria VALUES (1, 2), (1, 3), (2, 1), (3, 4), (3, 5);