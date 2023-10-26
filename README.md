# Libreria_BBDD_java
Diseño de programa para la gestión de base de datos relacionales y persistencia de objetos

1 Crear un programa para gestionar información sobre libros almacenando la informacion en MySQL.
Crear la clase "LibreriaMain" que muestra al usuario un menú con 6 opciones:
  -Restablecer la base de datos: esta opción debe:
      -Borrar la base de datos si ya existe
      -Crear la base de datos
      -Crear una tabla libro para almacenar: título, autor, país, paginas, género y un
      identificador autoincremental.
      -Crear una tabla autor para almacenar: nombre y los apellidos del autor y un
      identificador autoincremental.
      -Añadir dos autores.
      -Para cada uno de los dos autores anteriores, añadir un libro suyo.
  -Mostrar autores: esta opción debe de mostrar un listado de los autores.
  -Mostrar libros: esta opción debe mostrar un listado de los libros.
  -Modificar un autor, esta opción debe:
      -Mostrar un listado de los autores
      -Pedir qué autor se quiere modificar según su identificador
      -Pedir qué dato se quiere modificar del autor
      -Actualizar en la base de datos la información del autor.
  -Eliminar un libro, esta opción debe:
      -Mostrar un listado de los libros
      -Pedir qué libro se quiere eliminar según su identificador
      -Borrar el libro de la base de datos
      
