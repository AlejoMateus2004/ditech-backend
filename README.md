# **Ditech Backend**

Este proyecto es un pequeño microservicio desarrollado con Spring Boot que expone una API REST para la gestión de
usuarios. Permite realizar operaciones básicas como crear, listar, obtener y eliminar usuarios.
Requisitos

    Java 11 o superior.
    Maven 3.6.0 o superior.
    Git.

## Tecnologías Utilizadas

    Spring Boot: Framework para crear aplicaciones Java basadas en Spring.
    Spring Data JPA: Para la persistencia de datos.
    H2 Database: Base de datos en memoria para simplificar el desarrollo y las pruebas.
    Lombok: Para reducir el código boilerplate.

## Configuración del Proyecto

## Clonar el repositorio

Clona el repositorio en tu máquina local utilizando el siguiente comando:

```bash
    git clone https://github.com/AlejoMateus2004/ditech-backend.git
```
## Ejecución de la Aplicación

Para ejecutar la aplicación, utiliza el comando de Maven:

```bash
    mvn spring-boot:run
```
Este comando:

Compilará el proyecto.
Ejecutará la aplicación Spring Boot en el puerto predeterminado (8080).

Puedes acceder a la aplicación en: http://localhost:8080

## API REST Endpoints

Estos son los endpoints disponibles en la aplicación REST:

#### Crear un usuario:

**POST /users**

    {
      "username": "john_doe",
      "email": "john@example.com",
      "active": true
    }

#### Listar todos los usuarios:

**GET /users**

#### Obtener un usuario por ID:

**GET /users/{id}**

#### Eliminar un usuario:

DELETE /users

    {
        "id": 1,
        "username": "john_doe",
        "email": "john@example.com",
        "active": true
    }


## Ejecución de Tests

Para ejecutar los tests de la aplicación, usa el siguiente comando de Maven:

```bash
    mvn test
```

Este comando ejecutará todas las pruebas unitarias definidas para la aplicación y mostrará un reporte de los resultados
en la consola.