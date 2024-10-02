package controlador;

import javax.swing.JFrame;

import modelo.Modelo;
import vista.Vista;

public class Controlador {
	private Modelo miModelo;
	private Vista[] misVistas;

	public Controlador() {
		super();
	}

	public void setVista(Vista[] misVistas) {
		this.misVistas = misVistas;
	}

	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public Object[][] visualizarDatos() {
		Object [][] datos = miModelo.visualizarDatos();
		return datos;		
	}
}
