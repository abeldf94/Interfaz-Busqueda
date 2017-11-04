package com.abeldf.interfaz;
import java.awt.Point;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.abeldf.busqueda.AlgoritmoEstrella;
import com.abeldf.matrix.Matrix;

public class MyThread extends Thread {

	private ControladorInterfaz ci;

	public void run() {
		Matrix myMatrix = ci.getMyMatrix();
		Vector<Point> camino = ci.getCamino();
		JPanel leftPanel = ci.getLeftPanel();

		if (myMatrix.canStart()) {
			AlgoritmoEstrella solucionProblema = new AlgoritmoEstrella();
			camino = solucionProblema.buscar(myMatrix.getPosX(2), myMatrix.getPosY(2), myMatrix.getPosX(3), myMatrix.getPosY(3), myMatrix);

			if (camino == null) {
				JOptionPane.showMessageDialog(null, "No se ha encontrado solucion al problema", "Algoritmo", JOptionPane.ERROR_MESSAGE);
			} else {
				for (int i = camino.size() - 1; i >= 0; i--) {
					Point it = camino.get(i);
					if (it != null) {
						if ((it != camino.lastElement()) && (it != camino.firstElement())) {
							myMatrix.setValAt((int) it.getX(), (int) it.getY(), 4);
							leftPanel.repaint();
						}
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "ERROR: Asegurate de que esta definido el punto inicial, final y no hay otro camino solucion dibujado.", "Algoritmo", JOptionPane.ERROR_MESSAGE);
		}
	}

	public MyThread(ControladorInterfaz ci) {
		this.ci = ci;
	}
}
