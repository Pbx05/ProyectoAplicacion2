package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controlador.Controlador;
import vista.Vista;

public class Modelo {
	private Vista[] misVistas;
	private Controlador miControlador;
	private File usuarios;
	private File directorio;

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setVista(Vista[] misVistas) {
		this.misVistas = misVistas;
	}
	
	public Object[][] visualizarDatos() {
		directorio = new File("./src/Carpeta");
		File[] archivos = directorio.listFiles();
		Object[][] datos = new Object[archivos.length][3];
		String estado = "";
		if (archivos != null) {
			int i = 0;
			for (File archivo : archivos) {
				if(archivo.getName().endsWith(".txt")) {
					estado = "normal";
				}else if(archivo.getName().endsWith(".ser")){
					estado = "serializado";
				}else {
					estado = "comprimido";
				}
				if(archivo.getName().endsWith(".ser.zip")) {
					estado = "serializado y comprimido";
				}
				datos[i][0] = archivo.getName();
				datos[i][1] = archivo.length();
				datos[i][2] = estado;
				i++;
			}
		}
		return datos;
	}

	public boolean serializarEmpleado(String nombre, String dni, int edad, double sueldo, String genero) {
		Empleado nuevoEmpleado = new Empleado(nombre, dni, edad, sueldo, genero);
		
		try(FileOutputStream fileOut = new FileOutputStream("./src/Carpeta/"+dni+".ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)){

			objectOut.writeObject(nuevoEmpleado);
			System.out.println("El objeto empleado ha sido introducido en el archivo empleados.ser");
			return true;

		} catch (IOException e) {

			e.printStackTrace();
		}
		return false;
	}

	public void eliminarEmpleado(String nombreArchivo) {
		directorio = new File("./src/Carpeta");
		File[] archivos = directorio.listFiles();
		if (archivos != null) {
			int i = 0;
			for (File archivo : archivos) {
				if(archivo.getName().equals(nombreArchivo)) {
					archivo.delete();
				}

			}
		}
	}

	public boolean deserializarEmpleado(String nombreArchivo) {
		Empleado empleado = null;

		String nuevoNombre = nombreArchivo.substring(0,(nombreArchivo.length()-4));
		File archivoSer = new File("./src/Carpeta/"+nombreArchivo);

		try(FileInputStream archivo = new FileInputStream("./src/Carpeta/"+nombreArchivo);
				ObjectInputStream ois = new ObjectInputStream(archivo)){

			empleado = (Empleado) ois.readObject();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(empleado != null) {
			
			try(FileWriter writer = new FileWriter("./src/Carpeta/"+nuevoNombre+".txt")){
				writer.write(empleado.toString());
				archivoSer.delete();
				
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return false;
		
	}


}
