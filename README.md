# Rescate de Patitas

## Trabajo Práctico - GRUPO 7 - Diseño de Sistemas - 2021 - Jueves Mañana

| ALUMNO                            | LEGAJO        |               
| ----------------------------------|:-------------:|
| Alexis Herasimiuk                 | 167.251-4     |
| Ian Crespi                        | 167.397-0     |
| Emmanuel Lazarte                  | 168.926-5     |
| Nicolás Williman                  | ???.???-?     |
| Federico Silva                    | 167.439-0     |
| Camil Loyola                      | ???.???-?     |


Ayudante: fedekiwo

----

## Diagrama de Clases

![diagramaDeClases](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/dds-utn/2021-ju-ma-grupo-7/main/diagramaDeClases.plantuml?token=AKL4DPUCCHGKQ4VV227VBSLAS3XM4)



----

# Justificaciones de Diseño


1 - Relación Usuario - Dueño - Administrador - Persona - Rescatista

En primer lugar surgió la abstracción persona dado que son atributos (y posiblemente en el futuro comportamiento) que comparten tanto un `Dueño` como un `Rescatista`.

De la misma manera, existen en el sistema `Usuario`s que son los dueños de las mascotas, que poseen usuario y contraseña al igual que los `Administrador`es. 

Nos debatimos entre las posibilidades de herencia y composición.

Finalmente se decide que `Dueño` y `Administrador` tengan como atributo a `Usuario`; `Dueño` y `Rescatista` heredan de `Persona`, y el `Rescate` tiene como atributo a un `Rescatista`.

De esta manera nos evitamos (en el caso de que `Rescatista` herede de `Persona`), que un Dueño sea un Rescatista, así como que un `Rescate` pueda ser registrado por un `Dueño`

2 - No queremos usar Singleton

La clase `Registro` contiene los registros de los `Dueño`s, `Mascota`s y `Rescate`s; como sabemos debe haber un único registro en el sistema. Decidimos acordar que exista una única instancia de `Registro` en el sistema que sea usado en todo momento. 
Lo mismo sucede con la clase `Características` que contiene todas las `Caracteristica`s que el `Administrador` ha decidido.

3 - Mayor robustez y extensibilidad en `Característica`

`Característica` es más robusto y extensible ya que predefinimos las posibles opciones para cada característica, y en el futuro se podrán hacer consultas sobre ellas (por ejemplo, filtrar castrados).
Evitando que suceda algo como lo siguiente

```java
mascota.agregarCaracteristica("Castrado", "SI")
otraMascota.agregarCaracteristica("Castrada", "negativo")
```

Por otro lado, los títulos y opciones se pasan a mayúsculas para lograr mayor consistencia.


4 - IDs para identificar `Dueño` y `Mascota`

`Dueño` y `Mascota` tienen ID's ya que Dueño tiene una serie de mascotas y cada mascota además debe conocer a su dueño. Para no provocar una referencia circular, decidimos que sea mediante ID's. 
Los registros quedan centralizados en la clase `Registro`









# Plantilla del Proyecto


Esta es una plantilla de proyecto diseñada para: 

* Java 8. :warning: Si bien el proyecto no lo limita explícitamente, el comando `mvn verify` no funcionará con versiones mas modernas de Java. 
* JUnit 5. :warning: La versión 5 de JUnit es la más nueva del framework y presenta algunas diferencias respecto a la versión "clásica" (JUnit 4). Para mayores detalles, ver: 
  *  [Apunte de herramientas](https://docs.google.com/document/d/1VYBey56M0UU6C0689hAClAvF9ILE6E7nKIuOqrRJnWQ/edit#heading=h.dnwhvummp994)
  *  [Entrada de Blog (en inglés)](https://www.baeldung.com/junit-5-migration) 
  *  [Entrada de Blog (en español)](https://www.paradigmadigital.com/dev/nos-espera-junit-5/)
* Maven 3.3 o superior

# Ejecutar tests

```
mvn test
```

# Validar el proyecto de forma exahustiva

```
mvn clean verify
```

Este comando hará lo siguiente:

 1. Ejecutará los tests
 2. Validará las convenciones de formato mediante checkstyle
 3. Detectará la presencia de (ciertos) code smells
 4. Validará la cobertura del proyecto

# Entrega del proyecto

Para entregar el proyecto, crear un tag llamado `entrega-final`. Es importante que antes de realizarlo se corra la validación
explicada en el punto anterior. Se recomienda hacerlo de la siguiente forma:

```
mvn clean verify && git tag entrega-final && git push origin HEAD --tags
```

# Configuración del IDE (IntelliJ)

 1. Tabular con dos espacios: ![Screenshot_2021-04-09_18-23-26](https://user-images.githubusercontent.com/677436/114242543-73e1fe00-9961-11eb-9a61-7e34be9fb8de.png)
 2. Instalar y configurar Checkstyle:
    1. Instalar el plugin https://plugins.jetbrains.com/plugin/1065-checkstyle-idea:
    2. Configurarlo activando los Checks de Google: ![Screenshot_2021-04-09_18-16-13](https://user-images.githubusercontent.com/677436/114242548-75132b00-9961-11eb-972e-28e6e1412979.png)
 3. Usar fin de linea unix
    1. En **Settings/Preferences**, ir a a **Editor | Code Style**.
    2. En la lista **Line separator**, seleccionar `Unix and OS X (\n)`.
 ![Screenshot 2021-04-10 03-49-00](https://user-images.githubusercontent.com/11875266/114260872-c6490c00-99ad-11eb-838f-022acc1903f4.png)
