package com.abeldf.busqueda;
public class Nodo {

	public Nodo lastNodo;
	public Nodo fatherNodo;
	private int posicionX;
	private int posicionY;
	public int costoTotal;
	public int costoG;

	public Nodo(Nodo nodoPadre, Nodo nodoFinal, int x, int y, int costo) {

		fatherNodo = nodoPadre;
		lastNodo = nodoFinal;
		posicionX = x;
		posicionY = y;
		costoG = costo;

		if (nodoFinal != null) {
			costoTotal = costoG + calcularCosto();
		}
	}

	public int getX() {
		return posicionX;
	}

	public int getY() {
		return posicionY;
	}

	public int getCoste() {
		return costoG;
	}

	// Calcula distancia de Manhattan
	public int calcularCosto() {
		return Math.abs(this.getX() - lastNodo.getX()) + Math.abs(this.getY() - lastNodo.getY());
	}

	// Compara dos nodos
	public boolean esIgual(Nodo nodo) {
		if ((this.getX() == nodo.getX()) && (this.getY() == nodo.getY()))
			return true;
		else
			return false;
	}

}
