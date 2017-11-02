package com.abeldf.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.abeldf.constantes.PixelesImagen;
import com.abeldf.matrix.Matrix;

public class MiPanel extends JPanel {

	private ImageIcon noObstaculo;
	private ImageIcon obstaculo;
	private ImageIcon inicio;
	private ImageIcon fin;
	private ImageIcon camino;

	private Image noObstaculoResz;
	private Image obstaculoResz;
	private Image inicioResz;
	private Image finResz;
	private Image caminoResz;

	private Matrix myMatrix;

	private MouseListener myListenerInicio;
	private MouseListener myListenerFin;
	private MouseListener myListenerAdd;
	private MouseListener myListenerBorrar;

	private Boolean eliminar;
	private ControladorInterfaz ci;

	public MiPanel(ControladorInterfaz ci) {
		
		this.ci = ci;
		
		myMatrix = this.ci.getMyMatrix();

		this.setLayout(new GridLayout());

		noObstaculo = new ImageIcon("src/0.jpg");
		obstaculo = new ImageIcon("src/1.png");
		inicio = new ImageIcon("src/2.jpg");
		fin = new ImageIcon("src/3.jpg");
		camino = new ImageIcon("src/4.jpg");

		this.fixImg();
		this.calculaTamanioPanel();
		//Define listeners para el MouseEvent
		this.defineMouseListenerInicio();
		this.defineMouseListenerFin();
		this.defineMouseListenerAdd();
		this.defineMouseListenerBorrar();

		eliminar = false;
	}

	public void fixImg() {
		noObstaculoResz = noObstaculo.getImage();
		obstaculoResz = obstaculo.getImage();
		inicioResz = inicio.getImage();
		finResz = fin.getImage();
		caminoResz = camino.getImage();
	}

	public void calculaTamanioPanel() {
		this.setPreferredSize(new Dimension(PixelesImagen.ANCHO * myMatrix.getMatrix()[0].length, PixelesImagen.ALTO * myMatrix.getMatrix().length));
	}

	private void defineMouseListenerInicio() {
		myListenerInicio = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				realizaEventoInicio(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				return;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				return;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				return;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				return;
			}
		};
	}

	private void defineMouseListenerFin() {
		myListenerFin = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				realizaEventoFin(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				return;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				return;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				return;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				return;
			}
		};
	}
	
	private void defineMouseListenerAdd() {
		myListenerAdd = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				realizaEventoAdd(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				return;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				return;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				return;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				return;
			}
		};
	}

	private void defineMouseListenerBorrar() {
		myListenerBorrar = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				realizaEventoBorrar(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				return;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				return;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				return;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				return;
			}
		};
	}

	// Funcion que pinta el panel con el contenido de matriz, 0=Nada, 1=Obstaculo,
	// 2=Inicio, 3=Fin, 4=Camino de solucion
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		int posX = 0;
		int posY = 0;
		for (int i = 0; i < myMatrix.getMatrix().length; i++) {
			for (int j = 0; j < myMatrix.getMatrix()[i].length; j++) {
				if (myMatrix.getMatrix()[i][j] == 0) {
					g.drawImage(noObstaculoResz, posX, posY, PixelesImagen.ANCHO, PixelesImagen.ALTO, null);
				} else if (myMatrix.getMatrix()[i][j] == 1) {
					g.drawImage(obstaculoResz, posX, posY, PixelesImagen.ANCHO, PixelesImagen.ALTO, null);
				} else if (myMatrix.getMatrix()[i][j] == 2) {
					g.drawImage(inicioResz, posX, posY, PixelesImagen.ANCHO, PixelesImagen.ALTO, null);
				} else if (myMatrix.getMatrix()[i][j] == 3) {
					g.drawImage(finResz, posX, posY, PixelesImagen.ANCHO, PixelesImagen.ALTO, null);
				} else if (myMatrix.getMatrix()[i][j] == 4) {
					g.drawImage(caminoResz, posX, posY, PixelesImagen.ANCHO, PixelesImagen.ALTO, null);
				}
				posX += PixelesImagen.ANCHO;
			}
			posX = 0;
			posY += PixelesImagen.ALTO;
		}
	}

	// Registro eventos para colocar el punto inicial
	public void inicioClick() {
		//El if controla que no esten activas las funciones de fin, borrar o añadir obstaculos antes de activar este listener
		if ((!Arrays.asList(this.getMouseListeners()).contains(myListenerInicio)) && (!Arrays.asList(this.getMouseListeners()).contains(myListenerFin))&& (!Arrays.asList(this.getMouseListeners()).contains(myListenerAdd)) && (!Arrays.asList(this.getMouseListeners()).contains(myListenerBorrar))) {
			ci.getInicio().setBackground(Color.GREEN);
			this.addMouseListener(myListenerInicio);
		} else {
			JOptionPane.showMessageDialog(null, "ERROR: Ya hay una funcion activa", null, JOptionPane.ERROR_MESSAGE);
		}
	}

	public void realizaEventoInicio(MouseEvent e) {
		int x = e.getX() / PixelesImagen.ANCHO;
		int y = e.getY() / PixelesImagen.ALTO;

		try {
			myMatrix.setStart(y, x);
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}

		this.removeMouseListener(myListenerInicio);
		ci.getInicio().setBackground(Color.ORANGE);
		this.repaint();
	}

	// Registro eventos para colocar el punto final
	public void finClick() {
		//El if controla que no esten activas las funciones de inicio, borrar o añadir obstaculos antes de activar este listener
		if ((!Arrays.asList(this.getMouseListeners()).contains(myListenerFin)) && (!Arrays.asList(this.getMouseListeners()).contains(myListenerInicio)) && (!Arrays.asList(this.getMouseListeners()).contains(myListenerAdd)) && (!Arrays.asList(this.getMouseListeners()).contains(myListenerBorrar))) {
			ci.getFin().setBackground(Color.GREEN);
			this.addMouseListener(myListenerFin);
		} else {
			JOptionPane.showMessageDialog(null, "ERROR: Ya hay una funcion activa", null, JOptionPane.ERROR_MESSAGE);
		}
	}

	public void realizaEventoFin(MouseEvent e) {
		int x = e.getX() / PixelesImagen.ANCHO;
		int y = e.getY() / PixelesImagen.ALTO;

		try {
			myMatrix.setEnd(y, x);
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}

		this.removeMouseListener(myListenerFin);
		ci.getFin().setBackground(Color.ORANGE);
		this.repaint();
	}
	
	//Registro evento para añadir obstaculos
	public void addClick(JButton bt) {
		//El if controla que no esten activas las funciones de fin, inicio o borrar obstaculos antes de activar este listener
		if ((!Arrays.asList(this.getMouseListeners()).contains(myListenerFin)) && (!Arrays.asList(this.getMouseListeners()).contains(myListenerInicio)) && (!Arrays.asList(this.getMouseListeners()).contains(myListenerBorrar))) {
			if (eliminar) {
				bt.setBackground(Color.ORANGE);
				this.removeMouseListener(myListenerAdd);
				eliminar = false;
			} else {
				bt.setBackground(Color.GREEN);
				this.addMouseListener(myListenerAdd);
				eliminar = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "ERROR: Ya hay una funcion activa", null,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void realizaEventoAdd(MouseEvent e) {
		int x = e.getX() / PixelesImagen.ANCHO;
		int y = e.getY() / PixelesImagen.ALTO;

		try {
			if ((myMatrix.getValue(y, x) == 0) && (myMatrix.getValue(y, x)) != 2 && (myMatrix.getValue(y, x) != 3) && (myMatrix.getValue(y, x) != 4)) {
				myMatrix.setValAt(y, x, 1);
			}
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}

		this.repaint();
	}

	// Registro eventos para eliminar un obstaculo
	public void borrarClick(JButton bt) {
		//El if controla que no esten activas las funciones de fin, inicio o añadir obstaculos antes de activar este listener
		if ((!Arrays.asList(this.getMouseListeners()).contains(myListenerFin)) && (!Arrays.asList(this.getMouseListeners()).contains(myListenerInicio))&& (!Arrays.asList(this.getMouseListeners()).contains(myListenerAdd))) {
			if (eliminar) {
				bt.setBackground(Color.ORANGE);
				this.removeMouseListener(myListenerBorrar);
				eliminar = false;
			} else {
				bt.setBackground(Color.GREEN);
				this.addMouseListener(myListenerBorrar);
				eliminar = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "ERROR: Ya hay una funcion activa", null, JOptionPane.ERROR_MESSAGE);
		}
	}

	public void realizaEventoBorrar(MouseEvent e) {
		int x = e.getX() / PixelesImagen.ANCHO;
		int y = e.getY() / PixelesImagen.ALTO;

		try {
			if ((myMatrix.getValue(y, x) != 0) && (myMatrix.getValue(y, x) != 2) && (myMatrix.getValue(y, x) != 3) && (myMatrix.getValue(y, x) != 4)) {
				myMatrix.setValAt(y, x, 0);
			}
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}

		this.repaint();
	}
}