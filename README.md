# cards-microservice

Este es un ejercicio de microservicios para conocer los tipos de tarjetas a los que podemos aplicar de acuerdo a nuestros
gustos, salario mensual y edad.

## Tecnologías y frameworks utilizados

- Java 11.
- Spring Boot 2.6.1.
- JUnit 5.

## ¿Cómo correr el proyecto?

Preferentemente utiliza Eclipse IDE con el plugin de Spring Tool Suite o directamente Spring Tool Suite
y ejecuta el proyecto como una aplicación de Spring Boot con la herramienta Boot Dashboard que se encuentra
justo al lado del botón de ejecución de este IDE.

## Consumo de la API

Una vez que el proyecto esté en ejecución, se puede utilizar Postman o Insonmia para realizar una petición GET a la siguiente url **localhost:8080/api/v1/cards**. Dicha url necesita recibir los siguientes parámetros:

- passion.
- salary.
- age.

### Nota

La URI regresará un código 400 si:

- Alguno de los parámetros de la petición no es incluido.

La URI regresará un código 404 si:

- No se encuantra alguna tarjeta aplicable a tu perfil


