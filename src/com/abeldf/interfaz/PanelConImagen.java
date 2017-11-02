package com.abeldf.interfaz;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelConImagen extends JPanel{
	
	private ImageIcon fondo;
	private Image fondoFix;
	
	public PanelConImagen () {
		fondo = new ImageIcon("src/fondo.png");
		fondoFix = fondo.getImage();		
	}

	public void paintComponent(Graphics g) {
		g.drawImage(fondoFix, 0, 0, null);
	}
}
