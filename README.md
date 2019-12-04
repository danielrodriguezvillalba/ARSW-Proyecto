# ARSW-Proyecto 

## Aplicación de ruleta 

## Asignatura

* ARSW

## Integrantes

Nombres y Apellidos | GitHub Username
--- | ---
Sergio Alejandro Nuñez Mendivelso | checho1998
Etienne Maxence Eugene Reitz | Tylones
Daniel Felipe Rodriguez Villalba | danielrodriguezvillalba
Miguel Angel Rojas Martinez | MiguelRmar

## Resumen 

Se realizara una aplicacion web de una ruleta, adaptandola a una experiencia multiusuario y un valor agregado muy atractivo.

Para esta aplicacion se plantea el siguiente diagrama de clases:

![Imagenes](https://github.com/danielrodriguezvillalba/ARSW-Proyecto/blob/master/imagenes/DiagramaClases.PNG)

Ademas podemos encontrar el diagrama del proceso principal de nuestra aplicacion con una notacion BPMN

![Imagenes](https://github.com/danielrodriguezvillalba/ARSW-Proyecto/blob/master/imagenes/Bizagi.png)

## Descripcion

El proyecto que se realizara es una aplicación web, representando una ruleta de casino, permitiendo asi la experiencia multiusuario, haciendo asi que cada uno apueste una cuota definida y se ganara con las reglas normales de una ruleta ademas tendra un valor agregado para que la aplicacion sea mas atractiva para los usuarios, en caso de no haber ningun ganador, la aplicacion es la que se queda con el total apostado.

## Valor Agregado

- El usuario puede estar jugando en varias salas al mismo tiempo.
- Historial de los ultimos numeros que han caido desde el momento en el que el usuario ingreso a una sala determinada.
- Usted como usuario podra ver en tiempo real las apuestas de las otras personas que esten con usted en la sala . 

## Reglas de la Ruleta

A continuación encontrará una rápida descripción de las diferentes reglas de la ruleta, así como los distintos tipos de apuestas posibles y sus respectivas ganancias.

- Por cada sala solo podran estar 4 usuarios jugando a la vez, si se desocupa un puesto otro usuario podra ocuparlo si asi lo desea.
- Simple (Sencilla) Apostar por un solo número. Gana 36 veces su apuesta si la ruleta se detiene sobre el número que ha elegido.
- Docena (por docenas) Apostar por 12 números. Gana 3 veces su apuesta si su apuesta es ganadora. Para realizar esta apuesta, coloque sus fichas sobre la casillas 12P, 12M ò 12P.
- 12P : 12 primeros números de la mesa (del 1 al 12)
- 12M : 12 números del medio de la mesa (del 13 al 14)
- 12D : 12 últimos números de la mesa (del 25 al 36)
- Dos docenas (doble docena) Apostar por 2 docenas. Gana la mitad de su apuesta si su apuesta es ganadora.
- Pasa o falta (mayor o menor) Apostar por un conjunto de números de la mesa comprendidos entre el 1 y el 18. Gana lo equivalente a su   apuesta si ésta resulta ganadora. Para realizar esta apuesta, coloque sus fichas sobre la casilla « falta ».
- Rojo o negro (red or black) Apostar exclusivamente por el color que saldrá.El cero no cuenta ni para uno ni para otro. Gana lo equivalente a su apuesta si ésta resulta ganadora.
- Par o impar (Odd or even) Apostar exclusivamente por que el número que salga sea par o impar. El cero no cuenta ni para uno ni para otro. Gana lo equivalente a su apuesta si ésta resulta ganadora siguiendo las reglas de la ruleta de casino.

## Atributos No funcionales

### Usabilidad

#### Escenarios

Escenarios donde esta un usuario en varias salas

1. Cliente: Usuario de la aplicacion
2. Estimulo: Saber cuando la otra sala esta en juego
3. Artefactos: Front end
4. Ambiente: Bajo condiciones normales de la aplicacion
5. Respuesta: Muestra los segundos que hacen falta para no dejar apostar mas y ponerse en juego
6. Medida de Respuesta: El usuario despues de haber apostado en una sala e ir a otra puede ver el tiempo restante de todas las salas en sus respectivas pestañas de su navegador.

Escenario:


## Git 


Escenarios donde dos Usuarios apuestan al tiempo

1. Cliente: Todos los usuarios del sistema
2. Estimulo: Tener una idea de donde estan apostando los otros usuarios en la misma sala con fichas de otro color y dar mayor realtime a la aplicacion
3. Artefactos: Front end, servidor de la base de datos y cache.
4. Ambiente: Bajo condiciones normales de la aplicacion.
5. Respuesta: Facilidad al ver las apuestas en nuestro tablero de los otros usuarios que estan en la misma sala.
6. Medida de Respuesta: Por cada click de los otros usuarios en el tablero de ellos se vera una ficha de otro color a el de nuestra apuesta en nuestro tablero y viceversa.

Escenario:


## Git 


## Interfaz de usuario 

La particularidad de nuestra aplicación es que permite a los usuarios jugar hasta 3 mesas diferentes al mismo tiempo. Por lo tanto, la interfaz debe ser capaz de adaptarse a un jugador que juega de 1 a 3 mesas al mismo tiempo : 

![](https://i.imgur.com/bpHMjpF.png)

En este diagrama, podemos ver cómo se verá la interfaz de usuario cuando esté conectada. Podemos ver una barra de menú, que permite al usuario acceder a su cuenta, recargar o retirar su saldo. 

En la parte izquierda de la pantalla, podemos ver una lista de las mesas abiertas, y cuántos jugadores están jugando actualmente en cada una de ellas. Haciendo clic en el nombre de una mesa, si todavía tiene plazas libres, el usuario podrá, si aún no está jugando en 3 mesas, unirse a la mesa deseada.

Finalmente, en el centro de la pantalla, tenemos la mesa de juego compuesta por la rueda de la ruleta, el tablero en el que el jugador puede apostar, la historia de los números ganadores así como la lista de jugadores en la mesa.

![](https://i.imgur.com/gHIZgjA.png)

En este diagrama, el usuario decidió jugar en dos mesas al mismo tiempo. Para adaptar la pantalla y permitir al usuario jugar en estas dos mesas en paralelo sin afectar a la visibilidad, la pantalla del juego se divide en dos horizontalmente, y ambas mesas son accesibles sin que el usuario tenga que desplazarse por la pantalla.

![](https://i.imgur.com/WZi0pEI.png)

Por último, para mostrar tres mesas al mismo tiempo, la parte horizontal superior se corta a la mitad, lo que permite mostrar hasta 3 mesas al mismo tiempo.

## Conexion base de datos 

Host: ec2-23-21-115-109.compute-1.amazonaws.com

Database: dcjlcvlpuum728

User: unkwzcvdxtrfys

Port: 5432

Password: 03eaed5577b5eadb70f2bbe4de0d68e23a967ab611eff45138dc2b690b0fe052

## HEROKU Deployment

https://arsw-proyecto.herokuapp.com

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e735ab13b7064ba9bae5a4de4c47250c)](https://www.codacy.com/manual/ProyectoARSW/ARSW-Proyecto?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=danielrodriguezvillalba/ARSW-Proyecto&amp;utm_campaign=Badge_Grade)

## Historias de usuario

https://tree.taiga.io/project/danielrodriguez-vi-arsw-proyecto/backlog

## Referencias:

- Reglas predeterminadas de la Ruleta: https://www.juegos-casino.org/reglas-ruleta.html


