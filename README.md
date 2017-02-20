# ipInformation

Esta aplicación recibe una IP y devuelve:

### El nombre y código ISO del país
### Los idiomas oficiales del país
### Hora(s) actual(es) en el país (si el país cubre más de una zona horaria, mostrar todas)
### Distancia estimada entre Buenos Aires y el país, en km.
### Moneda local, y su cotización actual en dólares (si está disponible)


## Pendientes

Quedaron pendientes para realizar:

### Agregar cache para evitar llamadas innecesarias a los servicios
### Agregar nas unit tests con mas casos y tests de integracion
### Mejorar el manejo de error en caso de que la IP no sea valida
### Mejorar la UI si es necesario

## Asunciones

Se asume que se va a correr en una maquina con java 8 o en una maquina con docker-engine instalado.

## Docker

Para crear la imagen de docker se debe correr el siguiente comando:

### ./gradlew build buildDocker

El build va a fallar al intentar hacer el push de la imagen ya que no esta registrado en el registry.
