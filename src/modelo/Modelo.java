package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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

	/**
	 * Metodo para visualizar todos los archivos que hay en la carpeta
	 * @return
	 */
	public Object[][] visualizarDatos() {
		// Creo un archivo file con el directorio donde voy a almacenar todos los archivos
		directorio = new File("./src/Carpeta");
		// Almaceno en un array de files todos los archivos con el metodo listFiles()
		File[] archivos = directorio.listFiles();
		// Hago un array bidimensional con la longitud del array de archivos para saber cuantos archivos hay y 3 columnas, que seran las columnas que tiene la tabla 
		Object[][] datos = new Object[archivos.length][3];
		// Creo un string estado vacio para llenarlo mas adelante
		String estado = "";
		// Hago un if para ver si archivos no es null
		if (archivos != null) {
			// Con esta variable i puedo hacer un contador para ir iterando y cambiar la fila de datos a rellenar
			int i = 0;
			// Hago un foreach para recorrer el array de archivos
			for (File archivo : archivos) {
				// Dentro hago un if para comprobar la extension del nombre del archivo,dependiendo de esta el estado sera distinto 
				if (archivo.getName().endsWith(".txt")) {
					estado = "normal";
				} else if (archivo.getName().endsWith(".ser")) {
					estado = "serializado";
				} else {
					estado = "comprimido";
				}
				if (archivo.getName().endsWith(".ser.zip")) {
					estado = "serializado y comprimido";
				}
				// Asigno a cada linea y columna su valor correspondiente 
				datos[i][0] = archivo.getName();
				datos[i][1] = archivo.length();
				datos[i][2] = estado;
				// sumo 1 a la i para iterar
				i++;
			}
		}
		return datos;
	}

	public boolean serializarEmpleado(String nombre, String dni, int edad, double sueldo, String genero) {
		Empleado nuevoEmpleado = new Empleado(nombre, dni, edad, sueldo, genero);

		try (FileOutputStream fileOut = new FileOutputStream("./src/Carpeta/" + dni + ".ser");
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

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
				if (archivo.getName().equals(nombreArchivo)) {
					archivo.delete();
				}

			}
		}
	}

	public boolean deserializarEmpleado(String nombreArchivo) {
		Empleado empleado = null;

		String nuevoNombre = nombreArchivo.substring(0, (nombreArchivo.length() - 4));
		File archivoSer = new File("./src/Carpeta/" + nombreArchivo);

		try (FileInputStream archivo = new FileInputStream("./src/Carpeta/" + nombreArchivo);
				ObjectInputStream ois = new ObjectInputStream(archivo)) {

			empleado = (Empleado) ois.readObject();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (empleado != null) {

			try (FileWriter writer = new FileWriter("./src/Carpeta/" + nuevoNombre + ".txt")) {
				writer.write(empleado.toString());
				archivoSer.delete();

				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;

	}
	
	/**
	 * Metodo para coger el nombre del archivo a comprimir
	 */
	public void comprimirArchivo(String nombreArchivoAComprimir) {
		// Con este String asigno el nuevoNombre al archivo 
		String nuevoNombre = "";
		// Compruebo si termina con .ser y si es asi lo dejo tal cual para que la extension mas adelante sea .ser.zip 
		if (nombreArchivoAComprimir.endsWith(".ser")) {
			nuevoNombre = nombreArchivoAComprimir;
		} else {
			// Sino es .ser entonces le quito la extension con un substring para que el nombre del zip sea el mismo que el del archivo solo 
			// que en vez de .txt sera .zip
			nuevoNombre = nombreArchivoAComprimir.substring(0, (nombreArchivoAComprimir.length() - 4));
		}
		// Creo nombreArchivoZip que sera un string con la ruta y el nombre donde se hara el zip
		String nombreArchivoZip = "./src/Carpeta/" + nuevoNombre + ".zip";
		// Creo un string con la ruta y el nombre del archivo que quiero zipear
		String archivoAZipear ="./src/Carpeta/" + nombreArchivoAComprimir;
		try {
			// Con este fileOutputStream creo el archivo zip
			FileOutputStream fos = new FileOutputStream(nombreArchivoZip);
			// Con este ZipOutputStream puedo escribir datos comprimidos en FileOutputStream anterior
			ZipOutputStream zos = new ZipOutputStream(fos);
			// Creo un archivo file del archivo que quiero zipear
			File file = new File(archivoAZipear);
			// Creo un string con el nombre del archivo
			String nombreArchivoCogido = file.getName();
			// creo un zipEntry para meter un archivo comprimido dentro del archivo zip
			ZipEntry zipEntry = new ZipEntry(nombreArchivoCogido);
			// Con esto indico que voy a comenzar a escribir la zipentry dentro del archivo zip
			zos.putNextEntry(zipEntry);

			// Con esto creo un flujo de entrada para leer los datos del archivo que quiero comprimir
			FileInputStream fis = new FileInputStream(file);
			// Con esto leo el archivo en pedazos de 1024 bytes para evitar problemas al leerlo todo de una vez
			byte[] buffer = new byte[1024];
			// Creo un entero donde vere los bytes que han sido leidos y asi escribirlos
			int length;
			// Con este bucle voy leyendo todos los datos y los escribo hasta que me quede sin datos por leer
			while ((length = fis.read(buffer)) >= 0) {
				zos.write(buffer, 0, length);
			}
			// Cierro todo
			zos.closeEntry();
			fis.close();
			// Elimino el archivo que acabo de zipear por que lo he metido en un zip
			file.delete();
			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para coger el nombre del archivo a descomprimir
	 */
	public void descomprimirArchivo(String nombreArchivoADescomprimir) {
		// ruta del archivo que quiero descomprimir
		String rutaZip = "./src/Carpeta/" + nombreArchivoADescomprimir;
		// Ruta donde almacenare los archivos que descomprima
		String direccionSalida = "./src/Carpeta/";
		// creo un objeto File con el archivo que quiero descomprimir
		File archivoComprimido = new File(rutaZip);
		// Representa el directorio de salida donde se extraerán los archivos.
		File dir = new File(direccionSalida);
		try {
			// Abre un flujo de entrada al archivo zip para poder leer su contenido.
			FileInputStream fis = new FileInputStream(rutaZip);
			// Con esto leo los archivos comprimidos dentro del zip, sabre que leer gracias al fileinputstream de antes
			ZipInputStream zis = new ZipInputStream(fis);
			// Representa cada archivo o directorio comprimido dentro del zip
			ZipEntry zipEntry;
			// Con el metodo getNextEntry avanzo a la siguiente entrada del zip mientras haya archivos el while sigue ejecutandose
			while ((zipEntry = zis.getNextEntry()) != null) {
				// aqui guardo la ruta donde saldra el archivo que descomprima del zip
				String rutaArchivo = direccionSalida + zipEntry.getName();
				// Creo un flujo para escribir en el archivo descomprimido en la ruta especificada
				FileOutputStream fos = new FileOutputStream(rutaArchivo);
				// Con esto leo el archivo en pedazos de 1024 bytes para evitar problemas al leerlo todo de una vez
				byte[] buffer = new byte[1024];
				// Creo un entero donde vere los bytes que han sido leidos y asi escribirlos
				int length;
				// Con este bucle voy leyendo todos los datos y los escribo hasta que me quede sin datos por leer
				while ((length = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, length);
				}
				// Cierro los flujos
				zis.closeEntry();
				fos.close();
			}
			// termino de cerrar los flujos fuera del bucle
			fis.close();
			zis.close();
			// Elimino el archivo comprimido por que lo he descomprimido
			archivoComprimido.delete();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
