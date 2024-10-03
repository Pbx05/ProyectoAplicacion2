package controlador;

import javax.swing.JFrame;
import javax.swing.JTextField;

import modelo.Modelo;
import vista.Vista;
import vista._00_Menu;
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

	public void comprimirArchivo() {
		String nombreArchivoAComprimir = ((_00_Menu)misVistas[0]).getArchivoSeleccionado();
		String nombreArchivo = ((_00_Menu)misVistas[0]).getTxtNombreArchivoZip().getText();
		miModelo.comprimirArchivo(nombreArchivo, nombreArchivoAComprimir);	
	}

	public void descomprimirArchivo() {
		String nombreArchivoADescomprimir = ((_00_Menu)misVistas[0]).getArchivoSeleccionado();
		miModelo.descomprimirArchivo(nombreArchivoADescomprimir);
	}
}
