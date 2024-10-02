package modelo;

import java.io.File;

import controlador.Controlador;
import vista.Vista;

public class Modelo {
	private Vista[] misVistas;
	private Controlador miControlador;
	File directorio;

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setVista(Vista[] misVistas) {
		this.misVistas = misVistas;
	}

	public Object[][] visualizarDatos() {
		directorio = new File("./src/Carpeta");
		File[] archivos = directorio.listFiles();
		Object[][] datos = new Object[archivos.length][2];
		if (archivos != null) {
			int i = 0;
			for (File archivo : archivos) {
				datos[i][0] = archivo.getName();
				datos[i][1] = archivo.length();
				i++;
			}
		}
		return datos;
	}
}
