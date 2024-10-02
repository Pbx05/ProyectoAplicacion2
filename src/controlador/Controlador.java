package controlador;

import javax.swing.JFrame;

import modelo.Modelo;
import vista.Vista;
import vista._01_CrearEmpleado;

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

	public void cambiarVentana(int desde, int hasta) {
		((JFrame) misVistas[desde]).setVisible(false);
		((JFrame) misVistas[hasta]).setVisible(true);
	}

	public boolean cogerDatosCrearUsuario() {
		String nombre = ((_01_CrearEmpleado) misVistas[1]).getTxtNombre().getText();
		String dni = ((_01_CrearEmpleado) misVistas[1]).getTxtDni().getText();
		int edad = Integer.parseInt(((_01_CrearEmpleado) misVistas[1]).getTxtEdad().getText());
		double sueldo = Integer.parseInt(((_01_CrearEmpleado) misVistas[1]).getTxtSueldo().getText());
		String genero = ((_01_CrearEmpleado) misVistas[1]).getTxtGenero().getText();

		return miModelo.serializarEmpleado(nombre, dni, edad, sueldo, genero);

	}
}
