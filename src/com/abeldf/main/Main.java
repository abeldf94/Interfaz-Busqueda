package com.abeldf.main;
import java.awt.EventQueue;

import com.abeldf.interfaz.ControladorInterfaz;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ControladorInterfaz();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
