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

	public Object[][] visualizarDatos() {
		directorio = new File("./src/Carpeta");
		File[] archivos = directorio.listFiles();
		Object[][] datos = new Object[archivos.length][3];
		String estado = "";
		if (archivos != null) {
			int i = 0;
			for (File archivo : archivos) {
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
				datos[i][0] = archivo.getName();
				datos[i][1] = archivo.length();
				datos[i][2] = estado;
				i++;
			}
		}
		return datos;
	}

	/**
	 * Método que Crea un objeto y lo serializa, para despues meterlo en un archivo
	 * serializado
	 * 
	 * @param nombre = input de usuario para nombre de Empleado
	 * @param dni    = input de usuario para dni de empleado
	 * @param edad   = input de usuario para asignar edad de empleado
	 * @param sueldo = input de usuario para asignar el sueldo al objeto de empleado
	 * @param genero = input de usuario para asignar el sueldo al empleado
	 * @return True = se ha serializado y creado el archivo / False = No se ha
	 *         serialiado y creado el archivo
	 */
	public boolean serializarEmpleado(String nombre, String dni, int edad, double sueldo, String genero) {
		// Creo un objeto de la clase empleado que estará serializado
		Empleado nuevoEmpleado = new Empleado(nombre, dni, edad, sueldo, genero);

		// Creo un fileoutputstream con la direccion a la que quiero crear el nuevo
		// archivo, y le añado la extension manualmente
		try (FileOutputStream fileOut = new FileOutputStream("./src/Carpeta/" + dni + ".ser");
				// Ahora le paso ese objeto a objectOutputStream
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

			// Escribo en el objetOutput el objeto serializado, y me crea un archivo con el
			// objeto serializado
			objectOut.writeObject(nuevoEmpleado);
			return true;

		} catch (IOException e) {

			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Método encargado de eliminar un fichero de la carpeta
	 * 
	 * @param nombreArchivo = nombre del archivo para buscar cual eliminar
	 */
	public void eliminarEmpleado(String nombreArchivo) {
		// Uso un objeto file, y le asigno la ruta de la carpeta con los archivos, para
		// que busque en esa dirección
		directorio = new File("./src/Carpeta");
		// En un array de Files, le paso el listFiles de la direccion que se le ha
		// marcado en la linea anterior, devolverá todos los archivos de la ruta
		File[] archivos = directorio.listFiles();
		if (archivos != null) {
			// Recorro un for each con un iterador file, que pasa por todos los files del
			// array "archivos"
			for (File archivo : archivos) {
				// Si el nombre del archivo es igual al parametro seleccionado para eliminar se
				// aplicará un delete
				if (archivo.getName().equals(nombreArchivo)) {
					archivo.delete();
				}

			}
		}
	}

	/**
	 * Método que deserializará un archivo y su contenido, para pasarlo de ".ser" a
	 * ".txt"
	 * 
	 * @param nombreArchivo = Nombre de un archivo de la carpeta que será buscado
	 *                      para su deserialización
	 * @return True = Se ha serializado el archivo / False = no se ha podido
	 *         deserializar el archivo
	 */
	public boolean deserializarEmpleado(String nombreArchivo) {
		Empleado empleado = null;

		// Saco el nombre del archivo, quitándole la extension, usando un substring
		String nuevoNombre = nombreArchivo.substring(0, (nombreArchivo.length() - 4));
		// Hago un nuevo fichero sin la extensión anterior
		File archivoSer = new File("./src/Carpeta/" + nombreArchivo);

		// Igual que para serializar, pero con "In" en vez de "Out"
		// le paso a un fileinputStream la ruta del nuevo archivo que se va a crear
		try (FileInputStream archivo = new FileInputStream("./src/Carpeta/" + nombreArchivo);
				// Le paso al objectInput el archivo de fileInput
				ObjectInputStream ois = new ObjectInputStream(archivo)) {

			// Le asigno a un objeto empleado la lectura del objectINput, que devolverá los
			// datos deserializados, lo que crea un objeto normal
			empleado = (Empleado) ois.readObject();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Si empleado ha sido rellena de datos entra en el if
		if (empleado != null) {

			// Creamos un fileWriter con una dirección con el nuevo nombre que hemos
			// modificado, añadiendo la extension .txt
			try (FileWriter writer = new FileWriter("./src/Carpeta/" + nuevoNombre + ".txt")) {
				// Escribo con el fileWriter el metodo toString de la clase empleado, con los
				// parámetros del objeto
				writer.write(empleado.toString());
				// Elimino el archivo antiguo
				archivoSer.delete();

				// Si realiza todos los pasos devolverá true
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Si en algun momento lanza una excepcion o no entra en algún if, devolverá
		// false
		return false;

	}

	public void comprimirArchivo(String nombreArchivoAComprimir) {
		String nuevoNombre = "";
		if (nombreArchivoAComprimir.endsWith(".ser")) {
			nuevoNombre = nombreArchivoAComprimir;
		} else {
			nuevoNombre = nombreArchivoAComprimir.substring(0, (nombreArchivoAComprimir.length() - 4));
		}
		String nombreArchivoZip = "./src/Carpeta/" + nuevoNombre + ".zip";
		String archivoAZipear = "./src/Carpeta/" + nombreArchivoAComprimir;
		try {
			FileOutputStream fos = new FileOutputStream(nombreArchivoZip);
			ZipOutputStream zos = new ZipOutputStream(fos);
			File file = new File(archivoAZipear);
			String nombreArchivoCogido = file.getName();
			ZipEntry zipEntry = new ZipEntry(nombreArchivoCogido);
			zos.putNextEntry(zipEntry);

			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) >= 0) {
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();
			fis.close();
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

	public void descomprimirArchivo(String nombreArchivoADescomprimir) {
		String rutaZip = "./src/Carpeta/" + nombreArchivoADescomprimir;
		String direccionSalida = "./src/Carpeta/";
		File archivoComprimido = new File(rutaZip);
		File dir = new File(direccionSalida);
		try {
			FileInputStream fis = new FileInputStream(rutaZip);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry zipEntry;
			while ((zipEntry = zis.getNextEntry()) != null) {
				String rutaArchivo = direccionSalida + zipEntry.getName();
				FileOutputStream fos = new FileOutputStream(rutaArchivo);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, length);
				}
				zis.closeEntry();
				fos.close();
			}
			fis.close();
			zis.close();
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
