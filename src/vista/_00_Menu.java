package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Modelo;

public class _00_Menu extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador miControlador;
	private Modelo miModelo;
	private JTable tableDatos;
	private DefaultTableModel modelo;
	private JLabel lblImagenPrincipio;
	private JButton btnAgregarArchivo;
	private JButton btnDeserializarArchivo;
	private JButton btnComprimirArchivo;
	private JButton btnDescomprimirArchivo;
	private JButton btnEliminarArchivo;
	private JScrollPane scrollPane;
	private Object[][] datos;
	private JLabel lblFondo;
	private JLabel lblFondoMandos;

	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	@Override
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}
	
	/**
	 * Metodo para devolver el nombre del archivo seleccionado en la tabla
	 * @return
	 */
	public String getArchivoSeleccionado() {
		return datos[tableDatos.getSelectedRow()][0] + "";
	}

	public _00_Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Muestro el tamaño del jPanel
		setBounds(100, 100, 713, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon imagenMandos = new ImageIcon("./src/Assets/Mandos.png");

		// Creo un scrollPane para meter la tabla y asi que pueda scrollear el usuario hacia bajo y si ya ha ocupado el espacio maximo la tabla
		scrollPane = new JScrollPane();
		scrollPane.setBounds(339, 0, 360, 220);
		contentPane.add(scrollPane);

		// Creo la tabla donde sacare los datos
		tableDatos = new JTable();
		scrollPane.setViewportView(tableDatos);
		tableDatos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableDatos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// Cada vez que haga click en un dato de la tabla cargara estos metodos para poner activo o desactivar cada boton dependiendo de el dato
				EstadoBotonDeserializar();
				EstadoBotonComprimir();
				EstadoBotonDescomprimir();
				EstadoBotonEliminar();
			}
		});
		
		// Label del titulo
		JLabel lblNewLabel = new JLabel("Gestor de archivos avanzado");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 329, 18);
		contentPane.add(lblNewLabel);

		// Boton para agregar un archivo
		btnAgregarArchivo = new JButton("Agregar Archivo");
		btnAgregarArchivo.setForeground(new Color(255, 255, 255));
		btnAgregarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cambiarVentana(0, 1);
			}
		});
		btnAgregarArchivo.setBackground(new Color(128, 0, 128)); 
		// Da relieve al borde del boton
		btnAgregarArchivo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
		btnAgregarArchivo.setFocusPainted(false);
		// Rellena el area del boton
		btnAgregarArchivo.setContentAreaFilled(true);
		// Para dar opacidad al fondo
		btnAgregarArchivo.setOpaque(true);
		btnAgregarArchivo.setBounds(443, 255, 172, 23);
		contentPane.add(btnAgregarArchivo);

		// Boton para deserializar
		btnDeserializarArchivo = new JButton("");
		btnDeserializarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Con este if llamo a un metodo en controlador para deserializar un archivo, este devolvera un boolean y si lo deserealiza 
				// entonces actualiza la tabla
				if (miControlador.recogerDatosDeserializarEmpleado()) {
					datos = miControlador.visualizarDatos();
					String[] columnas = { "Nombre", "Tamaño", "Estado" };
					modelo = new DefaultTableModel(datos, columnas);
					tableDatos.setModel(modelo);
				}
			}
		});
		btnDeserializarArchivo.setBackground(new Color(255, 204, 0));  
		btnDeserializarArchivo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY)); 
		btnDeserializarArchivo.setFocusPainted(false);  
		btnDeserializarArchivo.setContentAreaFilled(true); 
		btnDeserializarArchivo.setOpaque(true);
		btnDeserializarArchivo.setEnabled(false);
		btnDeserializarArchivo.setBounds(239, 95, 54, 48);
		contentPane.add(btnDeserializarArchivo);

		// Boton para comprimir un archivo
		btnComprimirArchivo = new JButton("");
		btnComprimirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamo al metodo del controlador al pulsar en el boton y una vez hace todo actualiza la tabla
				miControlador.comprimirArchivo();
				datos = miControlador.visualizarDatos();
				String[] columnas = {"Nombre", "Tamaño", "Estado"};
				modelo = new DefaultTableModel(datos, columnas);
				tableDatos.setModel(modelo);

			}
		});
		btnComprimirArchivo.setBackground(new Color(0, 102, 204));  // Color azul brillante
		btnComprimirArchivo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY)); // Borde en relieve
		btnComprimirArchivo.setFocusPainted(false);  // Evita el borde de enfoque
		btnComprimirArchivo.setContentAreaFilled(true); // Para rellenar el área del botón
		btnComprimirArchivo.setOpaque(true);  // Para que el fondo sea opaco
		btnComprimirArchivo.setEnabled(false);
		btnComprimirArchivo.setBounds(239, 170, 54, 48);
		contentPane.add(btnComprimirArchivo);

		// Metodo para descomprimir un archivo
		btnDescomprimirArchivo = new JButton("");
		btnDescomprimirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamo al metodo de controlador para descomprimir archivo y cuando lo haga actualizar la tabla  
				miControlador.descomprimirArchivo();
				datos = miControlador.visualizarDatos();
				String[] columnas = {"Nombre", "Tamaño", "Estado"};
				modelo = new DefaultTableModel(datos, columnas);
				tableDatos.setModel(modelo);

			}
		});
		btnDescomprimirArchivo.setBackground(new Color(0, 204, 0));
		btnDescomprimirArchivo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY)); 
		btnDescomprimirArchivo.setFocusPainted(false);
		btnDescomprimirArchivo.setContentAreaFilled(true);
		btnDescomprimirArchivo.setOpaque(true);
		btnDescomprimirArchivo.setEnabled(false);
		btnDescomprimirArchivo.setBounds(239, 239, 54, 48);
		contentPane.add(btnDescomprimirArchivo);

		// boton para eliminar un archivo
		btnEliminarArchivo = new JButton("");
		btnEliminarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Llamo al metodo del controlador para eliminar el archivo una vez lo hace actualiza la tabla
				miControlador.cogerDatosEliminarEmpleado();
				datos = miControlador.visualizarDatos();
				String[] columnas = { "Nombre", "Tamaño", "Estado" };
				modelo = new DefaultTableModel(datos, columnas);
				tableDatos.setModel(modelo);

			}
		});
		btnEliminarArchivo.setBackground(new Color(255, 0, 0));
		btnEliminarArchivo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
		btnEliminarArchivo.setFocusPainted(false); 
		btnEliminarArchivo.setContentAreaFilled(true); 
		btnEliminarArchivo.setOpaque(true);  
		btnEliminarArchivo.setEnabled(false);
		btnEliminarArchivo.setBounds(239, 309, 54, 48);
		contentPane.add(btnEliminarArchivo);

		ImageIcon iconoOriginal = new ImageIcon("./src/Assets/RefreshDef.png");
		Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
		
		// Label de deserializar un archivo
		JLabel lblDeserializarArchivo = new JLabel("Deserializar ");
		lblDeserializarArchivo.setForeground(new Color(255, 255, 255));
		lblDeserializarArchivo.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblDeserializarArchivo.setBounds(58, 111, 149, 23);
		contentPane.add(lblDeserializarArchivo);
		
		// Label de comprimir un archivo
		JLabel lblComprimirArchivo = new JLabel("Comprimir");
		lblComprimirArchivo.setForeground(new Color(255, 255, 255));
		lblComprimirArchivo.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblComprimirArchivo.setBounds(58, 187, 149, 23);
		contentPane.add(lblComprimirArchivo);
		
		// Label de descomprimir un archivo
		JLabel lblDescomprimirArchivo = new JLabel("Descomprimir");
		lblDescomprimirArchivo.setForeground(new Color(255, 255, 255));
		lblDescomprimirArchivo.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblDescomprimirArchivo.setBounds(58, 256, 149, 23);
		contentPane.add(lblDescomprimirArchivo);
		
		// Label de eliminar un archivo
		JLabel lblEliminarArchivo = new JLabel("Eliminar");
		lblEliminarArchivo.setForeground(new Color(255, 255, 255));
		lblEliminarArchivo.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblEliminarArchivo.setBounds(58, 327, 149, 23);
		contentPane.add(lblEliminarArchivo);
		
		ImageIcon imagen = new ImageIcon("./src/Assets/FondoEspacio.jpg");
		
		// Una imagen del fondo de la pantalla
		lblFondoMandos = new JLabel("New label");
		lblFondoMandos.setBounds(339, 230, 360, 203);
		contentPane.add(lblFondoMandos);
		Image imagenEscalada = imagenMandos.getImage().getScaledInstance(lblFondoMandos.getWidth(), lblFondoMandos.getHeight(), Image.SCALE_SMOOTH);
		lblFondoMandos.setIcon(new ImageIcon(imagenEscalada));

		// Imagen principal del fondo de la pantalla
		lblFondo = new JLabel(imagen);
		lblFondo.setBounds(0, 0, 699, 433);
		contentPane.add(lblFondo);
		lblFondo.setIcon(imagen);
		
		// Creo un window opened para que aparezcan todos los datos de la tabla al abrir la vista
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				// Llamo al metodo para visualizar los archivos que hay en la carpeta
				datos = miControlador.visualizarDatos();
				// Hago un array para indicar las columnas que tiene la tabla
				String[] columnas = { "Nombre", "Tamaño", "Estado" };
				// Creo un defaulttableModel donde guardo las filas con sus columnas
				modelo = new DefaultTableModel(datos, columnas);
				// Le asigno a la tabla el default table model
				tableDatos.setModel(modelo);
			}
		});
	}
	
	/**
	 * Metodo para activar o desactivar el boton deserializar dependiendo del estado 
	 */
	private void EstadoBotonDeserializar() {
		// Compruebo con un if si el estado de la linea seleccionada del array datos es igual a serializado, si lo es activo el boton si no lo es lo desactivo
		if(datos[tableDatos.getSelectedRow()][2].equals("serializado")) {
			btnDeserializarArchivo.setEnabled(true);
		}else {
			btnDeserializarArchivo.setEnabled(false);
		}	
	}
	
	/**
	 * Metodo para activar o desactivar el boton comprimir dependiendo del estado 
	 */
	private void EstadoBotonComprimir() {
		// Compruebo con un if si el estado de la linea seleccionada del array datos es igual a normal, si lo es activo el boton si no lo es lo desactivo
		if(datos[tableDatos.getSelectedRow()][2].equals("normal") || datos[tableDatos.getSelectedRow()][2].equals("serializado")) {
			btnComprimirArchivo.setEnabled(true);
		}else {
			btnComprimirArchivo.setEnabled(false);
		}	
	}
	
	/**
	 * Metodo para activar o desactivar el boton descomprimir dependiendo del estado 
	 */
	private void EstadoBotonDescomprimir() {
		// Compruebo con un if si el estado de la linea seleccionada del array datos es igual a comprimido, si lo es activo el boton si no lo es lo desactivo
		if(datos[tableDatos.getSelectedRow()][2].equals("comprimido") || datos[tableDatos.getSelectedRow()][2].equals("serializado y comprimido")) {
			btnDescomprimirArchivo.setEnabled(true);
		}else {
			btnDescomprimirArchivo.setEnabled(false);
		}	
	}
	
	/**
	 * Metodo para activar o desactivar el boton eliminar dependiendo del estado 
	 */
	private void EstadoBotonEliminar() {
		if(tableDatos.getSelectedRow() != -1) {
			btnEliminarArchivo.setEnabled(true);
		}else {
			btnEliminarArchivo.setEnabled(false);
		}	
	}
}
