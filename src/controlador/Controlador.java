package controlador;

import javax.swing.JFrame;
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

	/**
	 *  Setter de las vista 
	 * @param misVistas
	 */
	public void setVista(Vista[] misVistas) {
		this.misVistas = misVistas;
	}

	/**
	 *  Setter del modelo
	 * @param miModelo
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}
	
	/**
	 * Metodo para cambiar de vista
	 * @param desde
	 * @param hasta
	 */
	public void cambiarVentana(int desde, int hasta) {
		((JFrame) misVistas[desde]).setVisible(false);
		((JFrame) misVistas[hasta]).setVisible(true);
	}

	/**
	 * Metodo para visualizar todos los archivos que hay en la carpeta
	 * @return
	 */
	public Object[][] visualizarDatos() {
		Object[][] datos = miModelo.visualizarDatos();
		return datos;
	}

	/**
	 * Metodo para coger todos los datos de los textFields para la creacion de un usuario 
	 * @return
	 */
	public boolean cogerDatosCrearUsuario() {
		try {
			String nombre = ((_01_CrearEmpleado) misVistas[1]).getTxtNombre().getText();
			String dni = ((_01_CrearEmpleado) misVistas[1]).getTxtDni().getText();
			int edad = Integer.parseInt(((_01_CrearEmpleado) misVistas[1]).getTxtEdad().getText());
			double sueldo = Integer.parseInt(((_01_CrearEmpleado) misVistas[1]).getTxtSueldo().getText());
			String genero = ((_01_CrearEmpleado) misVistas[1]).getTxtGenero().getText();
			
			return miModelo.serializarEmpleado(nombre, dni, edad, sueldo, genero);
			
		}catch(NumberFormatException e) {
			return false;
		}


	}

	/**
	 * Metodo para coger el nombre del archivo a eliminar
	 */
	public void cogerDatosEliminarEmpleado() {
		// Asigno a una variable de tipo String el nombre del archivo gracias al metodo getArchivoSeleccionado que lo obtenemos casteando la vista
		String nombreArchivo = ((_00_Menu) misVistas[0]).getArchivoSeleccionado();
		miModelo.eliminarEmpleado(nombreArchivo);
	}

	/**
	 * Metodo para coger el nombre del archivo a deserializar
	 * @return
	 */
	public boolean recogerDatosDeserializarEmpleado() {
		// Asigno a una variable de tipo String el nombre del archivo gracias al metodo getArchivoSeleccionado que lo obtenemos casteando la vista
		String nombreArchivo = ((_00_Menu) misVistas[0]).getArchivoSeleccionado();
		return miModelo.deserializarEmpleado(nombreArchivo);
	}

	/**
	 * Metodo para coger el nombre del archivo a comprimir
	 */
	public void comprimirArchivo() {
		// Asigno a una variable de tipo String el nombre del archivo gracias al metodo getArchivoSeleccionado que lo obtenemos casteando la vista
		String nombreArchivoAComprimir = ((_00_Menu) misVistas[0]).getArchivoSeleccionado();
		miModelo.comprimirArchivo(nombreArchivoAComprimir);
	}

	/**
	 * Metodo para coger el nombre del archivo a descomprimir
	 */
	public void descomprimirArchivo() {
		// Asigno a una variable de tipo String el nombre del archivo gracias al metodo getArchivoSeleccionado que lo obtenemos casteando la vista
		String nombreArchivoADescomprimir = ((_00_Menu) misVistas[0]).getArchivoSeleccionado();
		miModelo.descomprimirArchivo(nombreArchivoADescomprimir);
	}
}
