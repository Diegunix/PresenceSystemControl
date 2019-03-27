# Presence System Control
## Spring Boot, Hibernate, JPA, Mysql, Actuator, Swagger, MapStruct, Lombok.

### Objetivo

Design and implement the backend for a presence control system for the employees of a company. You need to take into consideration that the employees must always clock in and out by registering their fingerprint.
The “People” department will need:

• Access to the employee’s presence and time control.

• Generation of time sheets of effective worked hours.

• Reception of asynchronous notifications regarding: total absence time, absence anomalies (for example because of on-call duties), etc.


### Desarrollo

Para el desarrollo se ha dado por hecho que existe un servicio encargado del sensor de control de presencia y que enviara un String que contiene un SHA512 con los datos de la huella dactilar.

También se presupone un servicio de notificaciones al que se llamara cuando se necesita notificar algo.

Se utilizado una BBDD Mysql instalada en local que contiene un modelo de datos compuesto por dos tablas una con los datos del usuario y otra con los datos de los fichajes de esos usuarios.

La aplicación se compone de una gestión de usuarios en la que se puede listar todos, crear usuario, eliminar usuario, actualizar usuario, obtener los datos de usuario por id y obtener los datos de usuario por huella dactilar.

Además un usuario puede fichar mediante huella dactilar y se pueden obtener dos tipos de listados uno con los fichajes del usuario y otro con las horas activas por día del usuario. Ambos informes pueden ser filtrados por fechas.

Cada vez que el usuario ficha se ejecuta un proceso asíncrono que comprueba si existen fichajes anómalos en el mes en curso.


### TO DO
• Quedaría por hacer toda la gestión de errores y validaciones.

• Añadir mas test unitarios al proyecto.

• Añadir documentación a swagger.

• Funcionalidad futura añadir objeto calendario que indique las horas que tiene que trabajar un usuario por día.

• Funcionalidad para poder editar fichajes antiguos.

• Añadir autentificación.

• Añadir mas tipos de informes.

---