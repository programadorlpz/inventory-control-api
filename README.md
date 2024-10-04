
# Sistema de Inventario Automotriz - Backend (Spring Boot)

## Descripción

Este es el backend del sistema de inventario automotriz. Proporciona una API para gestionar productos, usuarios, y roles en el inventario de una empresa automotriz.

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Gradle

## Funcionalidades

- CRUD de productos.
- Búsqueda y filtrado de productos por nombre, usuario, y fechas.
- Gestión de usuarios.

## Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/usuario/inventory-control-api.git
```

2. Configura la base de datos PostgreSQL:

```sql
CREATE DATABASE inventory_db;
CREATE USER admin WITH PASSWORD '100785';
GRANT ALL PRIVILEGES ON DATABASE inventory_db TO admin;
```

3. Configura las propiedades de la base de datos en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/inventory_db
spring.datasource.username=admin
spring.datasource.password=100785
```

4. Ejecuta el proyecto:

```bash
./gradlew bootRun
```

El servidor estará disponible en `http://localhost:8080`.

## Estructura del Proyecto

```
src/
  main/
    java/
      com/gradle/
        controller/
        entity/
        repository/
        service/
    resources/
      application.properties
```

## Autor

Desarrollado por [Tu Nombre].
