API REST desarrollada con **Spring Boot** que simula el backend de la tienda Sodimac. Se puede gestionar productos y categorias con operaciones CRUD.

### Ejecutar el proyecto

```bash
.\mvnw.cmd spring-boot:run
```

### Documentación Swagger

Una vez levantada la app, acceder a:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Endpoints

### Categorías — `/categorias`

| Método   | Endpoint           | Descripción                        |
| -------- | ------------------ | ---------------------------------- |
| `GET`    | `/categorias`      | Listar todas las categorias        |
| `GET`    | `/categorias/{id}` | Obtener categoria por ID           |
| `POST`   | `/categorias`      | Crear una nueva categoria          |
| `PUT`    | `/categorias/{id}` | Actualizar una categoria existente |
| `DELETE` | `/categorias/{id}` | Eliminar una categoría             |

#### Body — POST `/categorias`

```json
{
  "nombre": "Herramientas",
  "descripcion": "Herramientas manuales y eléctricas para construcción y reparación"
}
```

#### Body — PUT `/categorias/{id}`

```json
{
  "nombre": "Herramientas Eléctricas",
  "descripcion": "Taladros, esmeriles, sierras y más"
}
```

---

### Productos — `/productos`

| Método   | Endpoint                    | Descripcion                      |
| -------- | --------------------------- | -------------------------------- |
| `GET`    | `/productos`                | Listar todos los productos       |
| `GET`    | `/productos/{id}`           | Obtener producto por ID          |
| `POST`   | `/productos`                | Crear un nuevo producto          |
| `PUT`    | `/productos/{id}`           | Actualizar un producto existente |
| `DELETE` | `/productos/{id}`           | Eliminar un producto             |
| `GET`    | `/productos/categoria/{id}` | Listar productos por categoria   |
| `GET`    | `/productos/marca/{marca}`  | Listar productos por marca       |
| `GET`    | `/productos/ofertas`        | Listar productos en oferta       |
| `GET`    | `/productos/buscar/{texto}` | Buscar productos por nombre      |

#### Body — POST `/productos`

```json
{
  "nombre": "Taladro Percutor 13mm 750W",
  "descripcion": "Taladro percutor con mandril de 13mm, ideal para concreto y madera",
  "marca": "RANKOR",
  "precioNormal": 189990,
  "precioTarjetaCmr": 170990,
  "urlImagen": "https://media.falabella.com/falabellaPE/138564627_01/w=1500,h=1500,fit=cover",
  "categoria": {
    "id": 1
  }
}
```

#### Body — PUT `/productos/{id}`

> Estos campos son obligatorios.

```json
{
  "nombre": "Taladro Percutor 13mm 800W",
  "marca": "Bosch",
  "precioNormal": 199990,
  "precioTarjetaCmr": 175990
}
```

---

## Script de base de datos

```sql
CREATE DATABASE IF NOT EXISTS db_sodimac
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_spanish_ci;

USE db_sodimac;

-- ============================================
--  TABLA: categorias
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
('Herramientas', 'Herramientas eléctricas y manuales', NULL),
('Taladros',     'Taladros y percutores',              1),
('Pinturas',     'Pinturas y accesorios',               NULL);

INSERT INTO productos (nombre, descripcion_tecnica, marca, precio_normal, precio_tarjeta_cmr, url_imagen, id_categoria) VALUES
('Taladro Percutor Dewalt', '20V Max, Mandril de 13mm', 'Dewalt', 459.00, 399.00, 'https://media.falabella.com/sodimacPE/4224612_01/w=1500,h=1500,fit=cover', 2),
('Taladro Bosch GSB 550',   '550W, Mandril de 13mm',   'Bosch',  289.00, 249.00, 'https://media.falabella.com/sodimacPE/3444597_01/w=1500,h=1500,fit=cover', 2);
```
