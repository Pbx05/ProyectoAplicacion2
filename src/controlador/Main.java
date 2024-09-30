package controlador;

import modelo.Modelo;
import vista.Vista;
import vista._00_Menu;

public class Main {

	public static void main(String[] args) {
		// Creo 4 objetos de cada una de las clases del mvc, para poder operar con ellas
		// y que se den a conocer entre ellas
		Modelo miModelo = new Modelo();
		Vista[] misVistas = new Vista[6];
		Controlador miControlador = new Controlador();

		misVistas[0] = new _00_Menu();
		
		miModelo.setVista(misVistas);
		miControlador.setVista(misVistas);
		miControlador.setModelo(miModelo);

		// Para que funcione con las vistas, uso un foreach para hacer el set tanto del
		// modelo como del controlador de todas las vistas
		for (Vista vista : misVistas) {
			vista.setModelo(miModelo);
			vista.setControlador(miControlador);
		}

		// Inicio la aplicación en la vista 0, la que tiene el menú
		_00_Menu frame = (_00_Menu) misVistas[0];

		frame.setVisible(true);
	}

}
