Endpoints actualmente funcionales:

| **GET** | `/productos` : Listar todos los productos disponibles. 
| **GET** | `/productos/{id}` : Obtiene la información de un solo producto por su ID.
| **POST** | `/productos` : Crea un nuevo producto en el inventario. 
| **PUT** | `/productos/{id}` : Actualiza los datos de un producto existente. 
| **DELETE** | `/productos/{id}` : Elimina permanentemente un producto. 

| **GET** | `/categorias` : Lista todas las familias de productos.
| **GET** | `/categorias/{id}` : Obtiene los detalles de una categoria especifica.
| **POST** | `/categorias` : Crea una nueva categoría.
| **PUT** | `/categorias/{id}` : Actualiza el nombre o descripción de una categoria.
| **DELETE** | `/categorias/{id}` : Elimina una categoría del sistema. |


Script de la base de datos:
//
CREATE DATABASE IF NOT EXISTS db_sodimac
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_spanish_ci;

USE db_sodimac;

-- ============================================
--  TABLA: categorias
--  Entidad: CategoriaModel
-- ============================================

CREATE TABLE categorias (
    id                  BIGINT          NOT NULL AUTO_INCREMENT,
    nombre              VARCHAR(255)    NOT NULL,
    descripcion         VARCHAR(255)    NULL,
    id_categoria_padre  BIGINT          NULL,

    CONSTRAINT pk_categorias PRIMARY KEY (id),
    CONSTRAINT fk_categoria_padre
        FOREIGN KEY (id_categoria_padre)
        REFERENCES categorias(id)
        ON DELETE SET NULL
);

-- ============================================
--  TABLA: productos
--  Entidad: ProductoModel
-- ============================================

CREATE TABLE productos (
    id                  BIGINT          NOT NULL AUTO_INCREMENT,
    nombre              VARCHAR(255)    NOT NULL,
    descripcion_tecnica TEXT            NULL,
    marca               VARCHAR(255)    NULL,
    precio_normal       DOUBLE          NOT NULL,
    precio_tarjeta_cmr  DOUBLE          NULL,
    url_imagen          VARCHAR(255)    NULL,
    id_categoria        BIGINT          NULL,

    CONSTRAINT pk_productos PRIMARY KEY (id),
    CONSTRAINT fk_producto_categoria
        FOREIGN KEY (id_categoria)
        REFERENCES categorias(id)
        ON DELETE SET NULL
);

-- ============================================
--  DATOS DE EJEMPLO
-- ============================================

INSERT INTO categorias (nombre, descripcion, id_categoria_padre) VALUES
('Herramientas',     'Herramientas eléctricas y manuales', NULL),
('Taladros',         'Taladros y percutores',              1),
('Pinturas',         'Pinturas y accesorios',              NULL);


INSERT INTO productos (nombre, descripcion_tecnica, marca, precio_normal, precio_tarjeta_cmr, url_imagen, id_categoria) VALUES
('Taladro Percutor Dewalt', '20V Max, Mandril de 13mm', 'Dewalt', 459.00, 399.00, 'https://media.falabella.com/sodimacPE/4224612_01/w=1500,h=1500,fit=cover', 2),
('Taladro Bosch GSB 550',   '550W, Mandril de 13mm',   'Bosch',  289.00, 249.00, 'https://media.falabella.com/sodimacPE/3444597_01/w=1500,h=1500,fit=cover',   2);
//