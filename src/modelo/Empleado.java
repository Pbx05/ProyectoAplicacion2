package modelo;

import java.io.Serializable;

// Creo una clase Empleado, que se encargará de construir los objetos que serán serializados e introducidos en archivos .ser
public class Empleado implements Serializable {
	// Parametro imlementado de la interfaz Serializable
	private static final long serialVersionUID = 1L;
	// Párametros del objeto a construir
	private String nombre;
	private String dni;
	private int edad;
	private double sueldo;
	private String genero;

	// Constructor que serializa el objeto
	public Empleado(String nombre, String dni, int edad, double sueldo, String genero) {
		this.nombre = nombre;
		this.dni = dni;
		this.edad = edad;
		this.sueldo = sueldo;
		this.genero = genero;
	}

	// Getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	// Método para mostrar los datos de cada objeto
	public String toString() {
		return "Empleado [nombre=" + nombre + ", dni=" + dni + ", edad=" + edad + ", sueldo=" + sueldo + ", genero="
				+ genero + "]";
	}

}
