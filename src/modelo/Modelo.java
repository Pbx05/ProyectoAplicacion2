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
