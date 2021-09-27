# Rescate de Patitas

## Trabajo Práctico - GRUPO 7 - Diseño de Sistemas - 2021 - Jueves Mañana

| ALUMNO                            | LEGAJO        |               
| ----------------------------------|:-------------:|
| Alexis Herasimiuk                 | 167.251-4     |
| Ian Crespi                        | 167.397-0     |
| Emmanuel Lazarte                  | 168.926-5     |
| Nicolás Williman                  | 163.690-0     |
| Federico Silva                    | 167.439-0     |
| Camil Loyola                      | 161.626-2     |


Ayudante: fedekiwo

----

# Diagrama de Clases

![diagramaDeClases](https://imgur.com/bpTYOPe.jpg)



----

# Ejecutar con Docker

## Instalar Docker y Docker Compose

Esto dependerá de tu sistema operativo, así que te recomiendo seguir las instrucciones específicas de cada uno.

## Setear la contraseña de la Base de Datos

```sh
cp docker-compose-example.yaml docker-compose.yaml
```

Abrir `docker-compose.yaml` con tu editor de texto y completar el campo `MARIADB_ROOT_PASSWORD=`

## Configurar el persistence.xml

```sh
cp src/main/resources/META-INF/persistence.sample.xml src/main/resources/META-INF/persistence.xml
```

Setear los siguientes valores:

```xml
<property name="hibernate.connection.url" value="jdbc:mysql://db/rescate_de_patitas" />
<property name="hibernate.connection.username" value="<Nombre de Usuario de la DB>" />
<property name="hibernate.connection.password" value="<Contraseña de la DB>" />
```

Puede ser que desees eventualmente ejecutar el proyecto en el entorno local, para ello es necesario que tu sistema conozca quién es `db`, se puede hacer con el siguiente comando:

```sh
sudo nano /etc/hosts
```

y escribir la siguiente línea:

```sh
127.0.0.1  db
```

## Ejecutar el script

El script `run.sh` contiene las siguientes opciones:

- run: Levanta la base de datos y el servidor
- clean: Elimina los archivos compilados de java
- install: Instala las dependencias
- package: Compila el proyecto
- all: Todas las anteriores
- reset_db: Elimina la db si existe y la vuelve a crear


```sh
chmod +x run.sh ## Una única vez
docker-compose up ## Construye y levanta los contenedores

# En otra terminal
./run.sh reset_db ## Elimina la db si existe, y la crea
./run.sh all ## Compila y ejecuta el proyecto
```

## Arrancar o detener el contenedor

```sh
docker-compose stop
docker-compose start
```



