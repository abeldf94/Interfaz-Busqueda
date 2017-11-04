# Interfaz Busqueda

* El objetivo del desarrollo de la actividad práctica consiste en la utilización de estrategias de búsqueda como propuesta de resolución en la determinación de trayectorias óptimas por parte de un coche autónomo.

*El entorno se supone rectangular de dimensiones M x N y constituido por celdas libres y ocupadas, donde un coche autónomo puede efectuar acciones de movimiento, una cada vez, desde la casilla actual a una de las 4-vecinas (Norte, Sur, Este u Oeste) que no se encuentre ocupada. Las casillas ocupadas corresponden a obstáculos. Las casillas libres corresponden con celdas libres de obstáculos.

*El coche autónomo dispone de un vector de percepción, constituido por un sensor de proximidad (SN, SO, SS, SE) por cada una de las direcciones de movimiento, que detecta si la celda vecina correspondiente está ocupado por algún obstáculo o es libre para continuar por ese camino.