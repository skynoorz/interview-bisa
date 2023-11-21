
# Entrevista Banco BISA

En esta entrevesta se lleva a cabo la prueba técnica en el cual es una APIs REST necesarias para una aplicación de gestión de clientes. El objetivo de estas APIs es permitir determinar
la accesibilidad del cliente en base a la información de referencias registradas.




## Requisitos

- Java JDK 17
- Postman
- Maven
## Run Locally

Clona el proyecto

```bash
  git clone https://link-to-project
```

Ve a la ubicación del proyecto

```bash
  cd interview
```

Install dependencies

```bash
  mvn clean package
```

Inicia el servidor local

```bash
  java -jar ./target/interview-1.0.0-SNAPSHOT.war
```

## Run with Docker

Ve a la ubicacion del proyecto clonado:

```bash
  cd interview
```

Creamos la imagen:

```bash
docker build -t interview-bisa-ronald .
```
Creando el contenedor en el puerto **8080**:

```bash
docker run -p 8080:8080 interview-bisa-ronald
```

Listo! el servidor esta corriendo bajo Docker

## Database Access

Se escogió la base de datos H2 debido a la simplicidad en el despliegue de aplicaciones de prueba y test.

Para tener acceso a la base de datos se debe dirigir a la direccion: `http://localhost:8080/h2-console` el puerto puede variar si esta ocupado.


## API Reference

#### Crear una nueva Persona

```http
  POST /persona
```
Body JSON:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nombre` | `string` | **Required**. El nombre de la persona |
| `apPaterno` | `string` | **Required**. El apellido Paterno de la persona |
| `apMaterno` | `string` | **Required**. El apellido Materno de la persona |
| `fechaNacimiento` | `string` | **Required**. La fecha de nacimiento de la persona en formato **yyyy-mm-dd** |
| `direccion` | `string` | **Required**. La direccion de la persona |
| `carnet` | `number` | **Required**. El numero de carnet de la persona |

#### Crear un nuevo Cliente

```http
  POST /cliente
```
Body JSON:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nombre` | `string` | **Required**. El nombre del cliente |
| `apPaterno` | `string` | **Required**. El apellido Paterno del cliente |
| `apMaterno` | `string` | **Required**. El apellido Materno del cliente |
| `fechaNacimiento` | `string` | **Required**. La fecha de nacimiento del cliente en formato **yyyy-mm-dd** |
| `direccion` | `string` | **Required**. La direccion del cliente |
| `carnet` | `number` | **Required**. El numero de carnet del cliente |
| `email` | `string` | El correo electronico del cliente |
| `telefono` | `number` | El numero de telefono del cliente |
| `ocupacion` | `string` | La ocupacion del cliente, por ejemplo 'Informático' |

#### Crear nueva Referencia

Crea una nueva referencia entre un Cliente y una Persona o un Cliente con un Cliente

```http
  POST /referencia
```
Body JSON:
``` json
{
    "cliente": {
        "id": number
    },
    "personaReferencia": {
        "id": number
    }
}
```

#### Elimina una referencia

Elimina una referencia otorgandole el id de la relación

```http
  DELETE /referencia/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `motivo`      | `string` | El motivo de la eliminación de la referencia |

#### Listar por accesibilidad

Lista los clientes de acuerdo con la accesibilidad del cliente

```http
  GET /referencia
```
La respuesta tiene el siguiente formato:

``` json
{
    "ACCESIBILIDAD TOTAL": {
        "BUENA": [
            {clientes...}
        ],
        "REGULAR": [
            {clientes...}
        ],
        "MALA": [
            {clientes...}
        ],
        "NULA": [
            {clientes...}
        ]
    },
    "REFERENCIAS TIPO CLIENTE": {
        "BUENA": [
            {clientes...}
        ],
        "REGULAR": [
            {clientes...}
        ],
        "MALA": [
            {clientes...}
        ],
        "NULA": [
            {clientes...}
        ]
    }
}
```

## Author
_Ronald Alcides Guarachi Enriquez - Software Engineer_
- [@skynoorz](https://github.com/skynoorz)
