package com.abeldf.interfaz;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.abeldf.busqueda.AlgoritmoEstrella;
import com.abeldf.matrix.Matrix;

public class ControladorInterfaz {

	private JFrame ventana;

	private JScrollPane scroll;
	private MiPanel leftPanel;
	private PanelConImagen rightPanel;

	private Matrix myMatrix;
	private int filas;
	private int columnas;

	Vector<Point> camino;
	
	private JButton obstaculo;
	private JButton addObstaculo;
	private JButton deleteCasilla;
	private JButton resize;
	private JButton inicio;
	private JButton fin;
	private JButton resolver;
	private JButton limpiarSol;

	public ControladorInterfaz() {
		myMatrix = new Matrix();
		
		obstaculo = new JButton("Generar obstaculos");
		addObstaculo = new JButton("Añade obstaculos");
		deleteCasilla = new JButton("Eliminar obstaculo");
		resize = new JButton("Cambiar tamaño");
		inicio = new JButton("Punto inicial");
		fin = new JButton("Punto final");
		resolver = new JButton("Buscar camino");
		limpiarSol = new JButton("Eliminar solucion");
		
		parametrosIniciales();
	}

	private void parametrosIniciales() {
		JFrame miniVentana = new JFrame("Tamaño Muestra");
		miniVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		miniVentana.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);

		JLabel text = new JLabel("Nº Filas");
		text.setFont(new Font("Serif", Font.PLAIN, 18));
		miniVentana.add(text, gbc);

		JLabel text2 = new JLabel("Nº Columnas");
		text2.setFont(new Font("Serif", Font.PLAIN, 18));
		gbc.gridx++;
		miniVentana.add(text2, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JTextField col = new JTextField(10);
		JTextField fil = new JTextField(10);
		miniVentana.add(col, gbc);
		gbc.gridx++;
		miniVentana.add(fil, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 2;

		JButton acceptButton = new JButton("Aceptar");
		miniVentana.add(acceptButton, gbc);

		miniVentana.setSize(280, 180);
		miniVentana.setResizable(false);
		miniVentana.setLocationRelativeTo(null);
		miniVentana.setVisible(true);

		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ((col.getText().isEmpty()) && (fil.getText().isEmpty())) {
						Exception ex = new Exception("ERROR: Los campos no pueden estar vacios");
						;
						throw ex;
					} else {
						try {
							filas = Integer.parseInt(col.getText()); // Convierte el texto recogido a int (precaucion)
							columnas = Integer.parseInt(fil.getText());
						} catch (Exception ex) {
							ex = new Exception("ERROR: El contenido introducido debe ser numerico");
							throw ex;
						}
					}

					if ((filas <= 0) || (columnas <= 0)) {
						Exception ex = new Exception("ERROR: Los numeros deben ser mayor de 0");
						;
						throw ex;
					}

					myMatrix.resize(filas, columnas);
					miniVentana.dispose();
					start();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void start() {

		ventana = new JFrame("Inteligencia Artificial");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new BorderLayout());

		configuraLeftPanel();
		configuraRightPanel();

		ventana.pack(); 
		ventana.setLocationRelativeTo(null); // Coloca la interfaz centrada en la pantalla
		ventana.setVisible(true);
	}

	public void configuraLeftPanel() {

		leftPanel = new MiPanel(this);
		scroll = new JScrollPane(leftPanel);

		scroll.setPreferredSize(new Dimension(800, 500));
		scroll.getVerticalScrollBar().setUnitIncrement(15);

		ventana.add(scroll, BorderLayout.CENTER);
	}

	public void configuraRightPanel() {
		rightPanel = new PanelConImagen();
		rightPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		obstaculo.setBackground(Color.ORANGE);
		addObstaculo.setBackground(Color.ORANGE);
		deleteCasilla.setBackground(Color.ORANGE);
		resize.setBackground(Color.ORANGE);
		inicio.setBackground(Color.ORANGE);
		fin.setBackground(Color.ORANGE);
		resolver.setBackground(Color.ORANGE);
		limpiarSol.setBackground(Color.ORANGE);

		rightPanel.add(inicio, gbc);
		gbc.gridy++;

		rightPanel.add(fin, gbc);
		gbc.gridy++;

		rightPanel.add(obstaculo, gbc);
		gbc.gridy++;
		
		rightPanel.add(addObstaculo, gbc);
		gbc.gridy++;

		rightPanel.add(deleteCasilla, gbc);
		gbc.gridy++;

		rightPanel.add(resize, gbc);
		gbc.gridy++;

		rightPanel.add(resolver, gbc);
		gbc.gridy++;

		rightPanel.add(limpiarSol, gbc);

		obstaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obstaculo.setBackground(Color.GREEN);
				
				JTextField percentageField = new JTextField(5);

			    JPanel myPanel = new JPanel();
			    myPanel.add(new JLabel("Introduce el porcentage:"));
			    myPanel.add(percentageField);

			    int result = JOptionPane.showConfirmDialog(null, myPanel, "Porcentage obstaculos", JOptionPane.OK_CANCEL_OPTION);
			    
			    if (result == JOptionPane.OK_OPTION) {
			    	try {
						int p = Math.abs(Integer.parseInt(percentageField.getText())); // Convierte el texto recogido a int (precaucion)
						myMatrix.obstacleRandomGenerator(p);
						ventana.repaint();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "ERROR: Debes introducir un numero entre 0 y 99", null, JOptionPane.ERROR_MESSAGE);
					}
				}
			    obstaculo.setBackground(Color.ORANGE);
			}
		});
		
		addObstaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leftPanel.addClick(addObstaculo);
			}
		});

		deleteCasilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leftPanel.borrarClick(deleteCasilla);
			}
		});

		resize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resize.setBackground(Color.GREEN);
				
				JTextField xField = new JTextField(5);
			    JTextField yField = new JTextField(5);

			    JPanel myPanel = new JPanel();
			    myPanel.add(new JLabel("X:"));
			    myPanel.add(xField);
			    myPanel.add(Box.createHorizontalStrut(15)); // Espacio
			    myPanel.add(new JLabel("Y:"));
			    myPanel.add(yField);

			    int result = JOptionPane.showConfirmDialog(null, myPanel, "Tamaño Muestra", JOptionPane.OK_CANCEL_OPTION);
			    
			    if (result == JOptionPane.OK_OPTION) {
			    	try {
				    	if ((xField.getText().isEmpty()) && (yField.getText().isEmpty())) {
							Exception ex = new Exception("ERROR: Los campos no pueden estar vacios");
							throw ex;
						} else {
							try {
								filas = Integer.parseInt(xField.getText()); // Convierte el texto recogido a int (precaucion)
								columnas = Integer.parseInt(yField.getText());
							} catch (Exception ex) {
								ex = new Exception("ERROR: El contenido introducido debe ser numerico");
								throw ex;
							}
						}
				    	
				    	if ((filas <= 0) || (columnas <= 0)) {
							Exception ex = new Exception("ERROR: Los numeros deben ser mayor de 0");
							throw ex;
						}
				    	
				    	myMatrix.resize(filas, columnas);
				    	leftPanel.calculaTamanioPanel();
				    	scroll.repaint();
				    	scroll.revalidate();
				    }catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), null, JOptionPane.ERROR_MESSAGE);
					}
			    }
			    resize.setBackground(Color.ORANGE);
			}
		});

		inicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leftPanel.inicioClick();
			}
		});

		fin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leftPanel.finClick();
			}
		});

		limpiarSol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarSol.setBackground(Color.GREEN);
				int result = JOptionPane.showConfirmDialog(null, "Se va a proceder a borrar la solucion, ¿Continuar?", "Warning", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					myMatrix.limpiarSolucion();
					scroll.repaint();
				}
				limpiarSol.setBackground(Color.ORANGE);
			}
		});
		
		ControladorInterfaz ci = this;
		
		resolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				resolver.setBackground(Color.GREEN);
				String[] values = {"Mostrar solucion paso a paso", "Mostrar solucion"}; //Opciones para imprimir la solucion al algoritmo
				
				Object selected = JOptionPane.showInputDialog(null, "Tipo solucion:", "Algoritmo", JOptionPane.DEFAULT_OPTION, null, values, "0"); //Panel que recoge la eleccion y lo añade al objeto selected
				if ( selected != null ){ //null si el usuario cancela
				    String selectedString = selected.toString(); //Pasar el contenido de la eleccion
				    if (selectedString == "Mostrar solucion paso a paso"){
						MyThread th = new MyThread(ci); //Comienza un thread nuevo para poder pausarlo e ir imprimiendo 
						th.start();
				    }else if (selectedString == "Mostrar solucion") {
				    	if (myMatrix.canStart()) { //Si esta el punto inicial, final y no hay otra solucion ya pintada
							AlgoritmoEstrella solucionProblema = new AlgoritmoEstrella();
							camino = solucionProblema.buscar(myMatrix.getPosX(2), myMatrix.getPosY(2), myMatrix.getPosX(3), myMatrix.getPosY(3), myMatrix);

							if (camino == null) {
								JOptionPane.showMessageDialog(null, "No se ha encontrado solucion al problema", "Algoritmo", JOptionPane.ERROR_MESSAGE);
							} else {
								for (Point it : camino) {
									if (it != null) {
										myMatrix.setValAt((int) it.getX(), (int) it.getY(), 4); //Coloca un 4 en la matriz en la posicion (x,y). El cuatro simboliza camino solucion para la aplicacion
									}
								}
								leftPanel.repaint(); //Llama al paintComponent de los padres para actualizar el contenido del JPanel
							}
						} else {
							JOptionPane.showMessageDialog(null, "ERROR: Asegurate de que esta definido el punto inicial, final y no hay otro camino solucion dibujado.", "Algoritmo", JOptionPane.ERROR_MESSAGE);
						}
				    }
				}
				resolver.setBackground(Color.ORANGE);
			}
		});

		rightPanel.setPreferredSize(new Dimension(200, 500));
		ventana.add(rightPanel, BorderLayout.EAST);
	}
	
	/* Getters */

	public JFrame getFrame() {
		return ventana;
	}

	public MiPanel getLeftPanel() {
		return leftPanel;
	}

	public Matrix getMyMatrix() {
		return myMatrix;
	}

	public Vector<Point> getCamino() {
		return camino;
	}
	
	public JButton getInicio() {
		return inicio;
	}

	public JButton getFin() {
		return fin;
	}
}
