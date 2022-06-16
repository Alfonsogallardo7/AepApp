# AepApp

AepApp es una aplicación con la que podemos consultar, sobre los distintos campeonatos organizados por la Asociación Española de Powerlifting, además de un soporte donde se podrán organizar y ver los distintos campeonatos, con atletas y jueces.

## Autor

- Alfonso Gallardo Rodríguez

### Entidades

- Competición
- Competidor
- Juez
- Club

## Arrancar el proyecto

Para arrancar, aunque esta subido a un servidor web, sería conveniente que tambien esté corriendo en local. Para ello deberemos de seguir los siguientes pasos.

### - Descargar e instalar IntelliJ IDEA Community Edition 2021.2.2

### - Descargar JAVA JDK 17

### - Abrimos el IntelliJ IDEA

### - Configuramos la version de JDK

    1. Clickamos en File
    2. A continuación en Proyect Estructure
    3. Por último seleccionamos en Proyect SDK la versión 17 (la cual hemos descargado previamente)

### - Colección de Postman
Tambien se ofrece una colección de postman, donde poder probar todas las peticiones

    1. Descargamos y descomprimimos la colección de postman
    2. La importamos al nuestro
### - Arrancamos el Proyecto 

    1. Hacemos click en el botón de Maven,la cual encontraremos en la esquina superior derecha de la ventana
    2. A continuación clickamos en Pluggins.
    3. Después en Spring Boot
    4. Y por último en Spring Boot Run

## - ¿Qué podemos hacer en AepApp?

- En la versión movil

Nos encontraremos con un login y registro, donde deberemos de darnos de alta para poder utilizar nuestra app
Una vez dados de alta, obtendremos una lista con los proximos y antigüos campeonatos.
Si pinchamos en alguno, podremos ver todos los detalles del campeonato.

- En la versión web

Esta parte está restringida, ya que solo podrá entrar los usuarios administradores, ya que se trata de un panel de gestión.
En el nos encontraremos un login, donde deberemos de meter nuestro usuario y contraseña de administrador.

Una vez nos hayamos logueado, nos encontraremos un panel, donde podremos dirigirnos a las distintas entidades.

En esa lista encontraremos la información básica de cada entida, además de distintos botones donde podremos ver los detalles de la entidad, borrar una entidad, crear una entidad y editar una entidad