# Documentación tetris:
## Implementación del juego
- Se implementan distintas clases con los distintos componenetes del Tetris. Se alamacenarán en la carpeta ***src/game/elements***.
- Para comenzar, se definen las 7 piezas que conforman el juego. Estas serán las que debemos de colocar en la malla de elementos. Están numerados del 1-7 y se encuentran en la carpeta ***src/game/elements/pieces***, en estas se definen las distintas rotaciones que puede adoptar una figura. Además de la super clase `piece`, de la que heredan las mencionadas anteriormente.
- La clase ```bagSystemPieces``` se encarga de proporcionar al sistema las distintas piezas a colocar en la partida. De esta forma nos aseguramos de que todas las piezas salgan la misma cantidad de veces en una partida, además de la aleatoriedad del orden en el que aparecen.
- Por último, el objeto ```matrixTetris``` será el que almacene que piezas y en que posición se han colocado en cada jugada de la partida. Esta gestión se realiza mediante el uso de una matriz de enteros. Asímismo, esta clase se encarga de llevar el recuento del score de la partida, eliminar las filas completas y evidente registrar cada uno de las piezas colocadas en la partida.
### Forma de uso:

- Para probar el funcionamiento del videojuego y jugar a este, se crea la clase ```gameMainUser```. Requiere de una continua interacción con el usuario para apilar las piezas. Ejemplo de uso:
```
gameMainUser tetrisGame = new gameMainUser();
tetrisGame.playGame();
```
- Una vez comprobado el uso del juego, se ha implementado la clase ```gameMainSI```. Cuyo principal fin es que, mediante el uso de distintos SI, estos interactuen con el juego y decidan que jugada aplicar según el estado de la partida. Como resultado de la partida se devolverá el score final de esta. Importante destacar que se puede iniciar la clase con una pieza, en este caso la partida se jugará únicamente con esta pieza.
## Implementación de los Sistemas Inteligentes
- Para implementar completamente que un SI interactue automaticamente con el juego se crea la clase ```playerSI```. Que se encarga de interactuar con la clase ```gameMainSI```, se le acopla un sistema inteligente capaz de generar acciones para un estado concreto de la partida. Debido a la gran cantidad de distintas configuraciones y opciones que se dan para una partida se ha creado una clase ```configGame```, se explicará más adelante.


### SI Version 0
- Se crea una primera aproximación de un SI muy rudimentario. Se crea la interfaz `actionDecision` que implementa la toma de decisiones de un SI a partir de una situación de la partida. En esta primera iteración dado la matriz de piezas del tetris y sabiendo la próxima pieza a colocar se realiza una prueba a fuerza bruta. Se comprueban todas las acciones posibles y se genera una puntuación del resultado de ese movimiento, el movimiento con la mayor puntuación será el que se ejecute. Podemos ver la implementación de esta idea en la clase `src/SI_V0/modelsDecision`, en esta carpeta vemos dos versiones distintas.
    - _decisionV1_: solo se explora un nivel en el árbol de exploración de estados de la partida.
    - _decisionV2_: se exploran dos niveles de estados en el árbol de exploración, esto es, se comprueban los estados finales después de simular dos transiciones.
- Para generar el score a partir de un estado de la partida se crearon distintas heursisticas en `src/SI_V0/heuristic`, en esta carpeta se crean distintas heuristicas que crean un score a partir de un estado de la partida. Se crearon heuristicas basándose en el valor de los vecinos, a partir de las columnas, a partir de las filas, a partir de las filas con pesos...

### Config Game
- La clase `configGame`, definida en la carpeta `src/utils` permite modificar las distintas opciones de la partida, a continuación se detallan:

    -  _actionDecision_: será la clase que tome las decisiones, da soporte a los disintos SI para tomar la acción.
    -  _heuristic_: distintas heuristicas que complementan a las clases actionDecision.
    -  _iterations_: número máximo de iteraciones que se permiten en la partida, default 500.
    -  _scoreGapToStore_: diferencia de scores entre estados de la partida para que la acción se almacene, se usará después como dataSet para el entrenamiento de distintos SI, default 500.
    -  _printMatrix_: booleano que permite o no printear por pantalla la evolución de la partida, default false.
    -  _pieceUsed_: por si se quiere jugar una partida con una única pieza.
    -  _pathTrainRRNNA_: path para almecenar los datos de entrenamiento. 

### Análisis
- Para comprobar cuales son las opciones y qué SI son mejores se crea la clase análisis. El objetivo es crear arhivos auxiliares en los que se evalúan distintas métricas, con el fin de saber cuál es el mejor SI. El resultado del proceso de análisis son dos ficheros. Un primer fichero ***.log*** que contendrá los resultados de todas las pruebas de análisis y un fichero ***.result*** que recoge los resultados finales del análisis.
- Para usar esta clase debemos de usar el builder `analysisBuilder()`, cuyas opciones son las siguientes:
    - _setPieces_: introduce una lista con las piezas con las que se realizará el análisis jugando solamente con estas. 
    - _setActionDescion_: lista de actionDecision que a analizar.
    - _setHeuristic_: lista de heuristicas a analizar.
    - _setTopToSee_: cuantos resultados queremos ver en el top del archivo `.result`.
    - _setPrintResult_: boolean para posibilitar ver los resultados por pantalla.
    - _setDocOutput_: String con el path donde se escribirán los archivos.
### SI Version 1
- El objetivo principal es crear una RNA capaz de jugar al Tetris, para ello se crea la clase `nueralNetwork`, que implementa una RNA, además de métodos para entrenarla `backpropagation()`. En el paquete `src/SI_V1/RNA` encontramos las clases que dan forma a esta clase. Se definen los distintos elementos de una RNA y se implementan distintas funciones de activación en `src/SI_V1/RNA/activationFuncs`. 