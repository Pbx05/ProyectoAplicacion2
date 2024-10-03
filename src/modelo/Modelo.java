package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import controlador.Controlador;
import vista.Vista;

public class Modelo {
	private Vista[] misVistas;
	private Controlador miControlador;
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
		
		try(FileOutputStream fileOut = new FileOutputStream("./src/Carpeta/"+dni);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)){
			
			objectOut.writeObject(nuevoEmpleado);
			System.out.println("El objeto empleado ha sido introducido en el archivo empleados.ser");
			return true;
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	public void comprimirArchivo(String nombreArchivo, String nombreArchivoAComprimir) {
		String nombreArchivoZip = "./src/Carpeta/" + nombreArchivo + ".zip";
		String[] archivosAZipear = {"./src/Carpeta/" + nombreArchivoAComprimir};
		try {
			FileOutputStream fos = new FileOutputStream(nombreArchivoZip);
			ZipOutputStream zos = new ZipOutputStream(fos);
			for(String rutaArchivo : archivosAZipear) {
				File file = new File(rutaArchivo);
				String nombreArchivoCogido = file.getName();
				ZipEntry zipEntry = new ZipEntry(nombreArchivoCogido);
				zos.putNextEntry(zipEntry);
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int length;
				while((length = fis.read(buffer)) >= 0) {
					zos.write(buffer, 0, length);
				}
				zos.closeEntry();
				zos.close();
				fis.close();
				fos.close();
				file.delete();
			}
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
			while((zipEntry = zis.getNextEntry()) != null) {
				String rutaArchivo = direccionSalida + zipEntry.getName();
				FileOutputStream fos = new FileOutputStream(rutaArchivo);
				byte[] buffer = new byte[1024];
				int length;
				while((length = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, length);
				}
				zis.closeEntry();
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
