# ARSW-Proyecto 

## Aplicación de ruleta 

## Asignatura

* ARSW

## Integrantes

* Sergio Alejandro Nuñez Mendivelso
* Etienne Maxence Eugene Reitz
* Daniel Felipe Rodriguez Villalba
* Miguel Angel Rojas Martinez

## Resumen 

Se realizara una aplicacion web de una ruleta, adaptandola a una experiencia multiusuario y un valor agregado muy atractivo.

Para esta aplicacion se plantea el siguiente diagrama de clases:

![Imagenes](https://github.com/danielrodriguezvillalba/ARSW-Proyecto/blob/master/imagenes/DiagramaClases.PNG)

![Imagenes](https://github.com/danielrodriguezvillalba/ARSW-Proyecto/blob/master/imagenes/Bizagi.png)

## Descripcion

El proyecto que se realizara es una aplicación web, representando una ruleta de casino, permitiendo asi la experiencia multiusuario, haciendo asi que cada uno apueste una cuota definida y se ganara con las reglas normales de una ruleta ademas tendra un valor agregado para que la aplicacion sea mas atractiva para los usuarios, en caso de no haber ningun ganador, la aplicacion es la que se queda con el total apostado.

## Valor Agregado

- El usuario puede estar jugando en varias salas al mismo tiempo.
- Historial de los 10 Usuarios que poseen mas dinero en el casino, donde se podra ver cuanta plata tienen y si estan en el casino en ese momento en que se revisa el historial.
- Abra un premio acumulado de todas las salas, que se jugara en cualquier momento, en una sala al azar y con un numero al azar. 

## Reglas de la Ruleta

A continuación encontrará una rápida descripción de las diferentes reglas de la ruleta, así como los distintos tipos de apuestas posibles y sus respectivas ganancias.

- Por cada sala solo podran estar 5 usuarios jugando a la vez, si se desocupa un puesto otro usuario podra ocuparlo si asi lo desea.
- Simple (Sencilla) Apostar por un solo número. Gana 36 veces su apuesta si la ruleta se detiene sobre el número que ha elegido.
- Doble (Split) Apostar por 2 números. Gana 18 veces sus apuesta si la ruleta se detiene sobre uno de los números que usted ha elegido. Una apuesta a caballo se realiza colocando sus fichas sobre la línea común de 2 números.
- Ángulo (en Esquina) Apostar por 4 números. Gana 9 veces su apuesta si la ruleta se detiene sobre uno de los números que usted ha elegido. Para realizar esta apuesta, coloque sus fichas en la intersección de 4 números.
- Cuádruple (Cuatro números) Apostar por 4 números. Gana 9 veces su apuesta si la ruleta se detiene sobre uno de los números que usted ha elegido. Para realizar esta apuesta, coloque sus fichas en la intersección de 4 números.
- Docena (por docenas) Apostar por 12 números. Gana 2 veces su apuesta si su apuesta es ganadora. Para realizar esta apuesta, coloque sus fichas sobre la casillas 12P, 12M ò 12P.
- 12P : 12 primeros números de la mesa (del 1 al 12)
- 12M : 12 números del medio de la mesa (del 13 al 14)
- 12D : 12 últimos números de la mesa (del 25 al 36)
- Dos docenas (doble docena) Apostar por 2 docenas. Gana la mitad de su apuesta si su apuesta es ganadora.
- Pasa o falta (mayor o menor) Apostar por un conjunto de números de la mesa comprendidos entre el 1 y el 18. Gana lo equivalente a su   apuesta si ésta resulta ganadora. Para realizar esta apuesta, coloque sus fichas sobre la casilla « falta ».
- Rojo o negro (red or black) Apostar exclusivamente por el color que saldrá.El cero no cuenta ni para uno ni para otro. Gana lo equivalente a su apuesta si ésta resulta ganadora.
- Par o impar (Odd or even) Apostar exclusivamente por que el número que salga sea par o impar. El cero no cuenta ni para uno ni para otro. Gana lo equivalente a su apuesta si ésta resulta ganadora siguiendo las reglas de la ruleta de casino.

## Interfaz de usuario 

La particularidad de nuestra aplicación es que permite a los usuarios jugar hasta 4 mesas diferentes al mismo tiempo. Por lo tanto, la interfaz debe ser capaz de adaptarse a un jugador que juega de 1 a 4 mesas al mismo tiempo : 

![](https://i.imgur.com/bpHMjpF.png)

En este diagrama, podemos ver cómo se verá la interfaz de usuario cuando esté conectada. Podemos ver una barra de menú, que permite al usuario acceder a su cuenta, recargar o retirar su saldo. 

En la parte izquierda de la pantalla, podemos ver una lista de las mesas abiertas, y cuántos jugadores están jugando actualmente en cada una de ellas. Haciendo clic en el nombre de una mesa, si todavía tiene plazas libres, el usuario podrá, si aún no está jugando en 4 mesas, unirse a la mesa deseada.

Finalmente, en el centro de la pantalla, tenemos la mesa de juego compuesta por la rueda de la ruleta, el tablero en el que el jugador puede apostar, la historia de los números ganadores así como la lista de jugadores en la mesa.

![](https://i.imgur.com/gHIZgjA.png)

En este diagrama, el usuario decidió jugar en dos mesas al mismo tiempo. Para adaptar la pantalla y permitir al usuario jugar en estas dos mesas en paralelo sin afectar a la visibilidad, la pantalla del juego se divide en dos horizontalmente, y ambas mesas son accesibles sin que el usuario tenga que desplazarse por la pantalla.

![](https://i.imgur.com/WZi0pEI.png)

Por último, para mostrar tres o cuatro mesas al mismo tiempo, las partes horizontales se cortan por la mitad, lo que permite mostrar hasta 4 mesas al mismo tiempo.

## Conexion base de datos 

Host: ec2-23-21-115-109.compute-1.amazonaws.com

Database: dcjlcvlpuum728

User: unkwzcvdxtrfys

Port: 5432

Password: 03eaed5577b5eadb70f2bbe4de0d68e23a967ab611eff45138dc2b690b0fe052

## HEROKU Deployment

https://arsw-proyecto.herokuapp.com

## Historias de usuario

https://tree.taiga.io/project/danielrodriguez-vi-arsw-proyecto/backlog

## Referencias:

- Reglas predeterminadas de la Ruleta: https://www.juegos-casino.org/reglas-ruleta.html


