***Tetris:***

***Implementación del juego***

- Se implementan distintas clases con los distintos componenetes del Tetris. Se alamacenarán en la carpeta ***src/game/elements***.
- Para comnenzar, se definen las 7 piezas que conforman el juego y con las que se jugará, están numeradas del 1-7 y se encuentran en la carpeta ***src/game/elements/pieces***, en estas se definen las distintas rotaciones que puede adoptar una figura.
- La clase ```bagSystemPieces``` se encarga de gestionar la aparición de estas distintas piezas a lo largo de la partida y así asegurarnos de que todas las piezas salgan la misma cantidad de veces en una partida.
- Por último, el objeto ```matrixTetris``` será el que almacene que piezas y en que posición se han colocado en cada jugada de la partida. Esta gestión se realiza mediante el uso de una matriz de enteros. Asímismo, esta clase se encarga de llevar el recuento del score de la partida, elimnar las filas completas y evidente registrar cada uno de las piezas colocadas en la partida.

***Forma de uso:***

- Para probar el funcionamiento del videojuego y jugar a este, se crea la clase ```gameMainUser```. Requiere de una continua interacción con el usuario para apilar las piezas. Ejemplo de uso:
```
gameMainUser tetrisGame = new gameMainUser();
tetrisGame.playGame();
```
- Una vez comprobado el uso del juego, se ha implmentado la clase ```gameMainSI```. Cuyo principal fin es que, mediante el uso de distintos SI, estos interactuen con el juego y decidan que jugada aplicar según el estado de la partida. Como resultado de la partida se devolverá el score final de esta. Importante destacar que se puede iniciar la clase con una pieza, en este caso la partida se jugará únicamente con esta pieza.

- Para implementar completamente que un SI interactue automaticamente con el juego se crea la clase ```playerSI```. Que se encarga de interactuar con la clase ```gameMainSI```, debido a la gran cantidad de distintas configuraciones y opciones que se dan se ha creado una clase ```configGame``` para soportar todas estas opciones.

- Antes de explicar la clase `configGame` se van a explicar dos interfaces distintas que dan soporte a un primer ciclo del proyecto, obtener datos para nuestro dataSet, usado posteriormente en el entrenamiento de distintos SI.
 
  - 

- La clase ```configGame``` permite modificar las distintas opciones de la partida, a continuación se detallan:

    -  _actionDecision_: será la clase que tome las decisiones, da soporte a los disintos SI para tomar la acción.
    -  _heuristic_: distintas heuristicas que complementan a las clases actionDecision.
    -  _iterations_: número máximo de iteraciones que se permiten en la partida, default 500.
    -  _scoreGapToStore_: diferencia de scores entre estados de la partida para que la acción se almacene, se usará después como dataSet para el entrenamiento de distintos SI, default 500.
    -  _printMatrix_: booleano que permite o no printear por pantalla la evolución de la partida, default false.
    -  _pieceUsed_: por si se quiere jugar una partida con una única pieza.
    -  _pathTrainRRNNA_: path para almecenar los datos de entrenamiento. 

