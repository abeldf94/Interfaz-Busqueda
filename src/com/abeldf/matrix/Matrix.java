package com.abeldf.matrix;
import java.util.Random;

public class Matrix {

	int columnas;
	int filas;
	int myMatrix[][];

	public Matrix() {
	}

	public void setStart(int fil, int col) throws Exception {
		if (myMatrix[fil][col] != 2 && myMatrix[fil][col] != 3) {
			for (int i = 0; i < myMatrix.length; i++) {
				for (int j = 0; j < myMatrix[i].length; j++) {
					if (myMatrix[i][j] == 2) {
						myMatrix[i][j] = 0;
					}
				}
			}
			myMatrix[fil][col] = 2;
		} else if (myMatrix[fil][col] == 2) {
			Exception error = new Exception("ERROR: No puedes sobrescribir el punto inicial");
			throw error;
		} else if (myMatrix[fil][col] == 3) {
			Exception error = new Exception("ERROR: No puedes sobrescribir el punto final");
			throw error;
		}
	}

	public void setEnd(int fil, int col) throws Exception {
		if (myMatrix[fil][col] != 2 && myMatrix[fil][col] != 3) {
			for (int i = 0; i < myMatrix.length; i++) {
				for (int j = 0; j < myMatrix[i].length; j++) {
					if (myMatrix[i][j] == 3) {
						myMatrix[i][j] = 0;
					}
				}
			}
			myMatrix[fil][col] = 3;
		} else if (myMatrix[fil][col] == 2) {
			Exception error = new Exception("ERROR: No puedes sobrescribir el punto inicial");
			throw error;
		} else if (myMatrix[fil][col] == 3) {
			Exception error = new Exception("ERROR: No puedes sobrescribir el punto final");
			throw error;
		}
	}

	public void setValAt(int i, int j, int val) {
		myMatrix[i][j] = val;
	}

	public int getNumberColumns() {
		return columnas;
	}

	public int getNumberRow() {
		return filas;
	}

	public int getPosX(int val) {
		int aux = 0;
		for (int i = 0; i < myMatrix.length; i++) {
			for (int j = 0; j < myMatrix[i].length; j++) {
				if (myMatrix[i][j] == val) {
					aux = i;
				}
			}
		}
		return aux;
	}

	public int getPosY(int val) {
		int aux = 0;
		for (int i = 0; i < myMatrix.length; i++) {
			for (int j = 0; j < myMatrix[i].length; j++) {
				if (myMatrix[i][j] == val) {
					aux = j;
				}
			}
		}
		return aux;
	}

	public int getValue(int i, int j) {
		return myMatrix[i][j];
	}

	public void resize(int i, int j) {
		filas = i;
		columnas = j;
		myMatrix = new int[i][j];
	}

	public int[][] getMatrix() {
		return myMatrix;
	}

	public void obstacleRandomGenerator(int porcentaje) {

		/*
		 * Limpia la matriz de obstaculos para evitar que se generen de mas en caso de
		 * llamar a la funcion de nuevo
		 */
		for (int i = 0; i < myMatrix.length; i++) {
			for (int j = 0; j < myMatrix[i].length; j++) {
				if (myMatrix[i][j] == 1) {
					myMatrix[i][j] = 0;
				}
			}
		}
		/* Genera los obstaculos aleatoriamente por medio de la clase Random */
		Random rd = new Random();
		for (int i = 0; i < myMatrix.length; i++) {
			for (int j = 0; j < myMatrix[i].length; j++) {
				if (myMatrix[i][j] != 2 && myMatrix[i][j] != 3) { // Si no es el punto inicio o punto final
					if (rd.nextInt(99) < porcentaje) { // Solo metera un obstaculo si el valor generado entre 0 y 99 es
														// < que el porcentaje introducido
						myMatrix[i][j] = 1;
					}
				}
			}
		}
	}

	public void limpiarSolucion() {
		for (int i = 0; i < myMatrix.length; i++) {
			for (int j = 0; j < myMatrix[i].length; j++) {
				if (myMatrix[i][j] == 4) {
					myMatrix[i][j] = 0;
				}
			}
		}
	}

	public boolean canStart() {
		boolean encontradoInicio = false;
		boolean encontradoFin = false;
		boolean noEncontradoCamino = false;

		for (int i = 0; i < myMatrix.length; i++) {
			for (int j = 0; j < myMatrix[i].length; j++) {
				if (myMatrix[i][j] == 2) {
					encontradoInicio = true;
				} else if (myMatrix[i][j] == 3) {
					encontradoFin = true;
				} else if (myMatrix[i][j] == 4) {
					noEncontradoCamino = true;
				}
			}
		}

		if ((encontradoInicio) && (encontradoFin) && (!noEncontradoCamino)) {
			return true;
		}
		return false;
	}

}
