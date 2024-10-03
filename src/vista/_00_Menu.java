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
	
	public String getArchivoSeleccionado() {
		return datos[tableDatos.getSelectedRow()][0] + "";
	}

	public _00_Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFondoMandos = new JLabel("New label");
		lblFondoMandos.setBounds(339, 230, 360, 203);
		contentPane.add(lblFondoMandos);
		ImageIcon imagenMandos = new ImageIcon("./src/Assets/Mandos.png");
		Image imagenEscalada = imagenMandos.getImage().getScaledInstance(lblFondoMandos.getWidth(), lblFondoMandos.getHeight(), Image.SCALE_SMOOTH);
		lblFondoMandos.setIcon(new ImageIcon(imagenEscalada));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(339, 0, 360, 220);
		contentPane.add(scrollPane);

		tableDatos = new JTable();
		scrollPane.setViewportView(tableDatos);
		tableDatos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableDatos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				EstadoBotonDeserializar();
				EstadoBotonComprimir();
				EstadoBotonDescomprimir();
				EstadoBotonEliminar();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Gestor de archivos avanzado");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 329, 18);
		contentPane.add(lblNewLabel);

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

		btnDeserializarArchivo = new JButton("");
		btnDeserializarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

		btnComprimirArchivo = new JButton("");
		btnComprimirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

		btnDescomprimirArchivo = new JButton("");
		btnDescomprimirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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

		btnEliminarArchivo = new JButton("");
		btnEliminarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JLabel lblDeserializarArchivo = new JLabel("Deserializar ");
		lblDeserializarArchivo.setForeground(new Color(255, 255, 255));
		lblDeserializarArchivo.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblDeserializarArchivo.setBounds(58, 111, 149, 23);
		contentPane.add(lblDeserializarArchivo);
		
		JLabel lblComprimirArchivo = new JLabel("Comprimir");
		lblComprimirArchivo.setForeground(new Color(255, 255, 255));
		lblComprimirArchivo.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblComprimirArchivo.setBounds(58, 187, 149, 23);
		contentPane.add(lblComprimirArchivo);
		
		JLabel lblDescomprimirArchivo = new JLabel("Descomprimir");
		lblDescomprimirArchivo.setForeground(new Color(255, 255, 255));
		lblDescomprimirArchivo.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblDescomprimirArchivo.setBounds(58, 256, 149, 23);
		contentPane.add(lblDescomprimirArchivo);
		
		JLabel lblEliminarArchivo = new JLabel("Eliminar");
		lblEliminarArchivo.setForeground(new Color(255, 255, 255));
		lblEliminarArchivo.setFont(new Font("Sitka Heading", Font.BOLD, 20));
		lblEliminarArchivo.setBounds(58, 327, 149, 23);
		contentPane.add(lblEliminarArchivo);
		
		ImageIcon imagen = new ImageIcon("./src/Assets/FondoEspacio.jpg");

		lblFondo = new JLabel(imagen);
		lblFondo.setBounds(0, 0, 699, 433);
		contentPane.add(lblFondo);
		lblFondo.setIcon(imagen);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				datos = miControlador.visualizarDatos();
				String[] columnas = { "Nombre", "Tamaño", "Estado" };
				modelo = new DefaultTableModel(datos, columnas);
				tableDatos.setModel(modelo);
			}
		});
	}
	
	private void EstadoBotonDeserializar() {
		if(datos[tableDatos.getSelectedRow()][2].equals("serializado")) {
			btnDeserializarArchivo.setEnabled(true);
		}else {
			btnDeserializarArchivo.setEnabled(false);
		}	
	}
	
	private void EstadoBotonComprimir() {
		if(datos[tableDatos.getSelectedRow()][2].equals("normal") || datos[tableDatos.getSelectedRow()][2].equals("serializado")) {
			btnComprimirArchivo.setEnabled(true);
		}else {
			btnComprimirArchivo.setEnabled(false);
		}	
	}
	
	private void EstadoBotonDescomprimir() {
		if(datos[tableDatos.getSelectedRow()][2].equals("comprimido") || datos[tableDatos.getSelectedRow()][2].equals("serializado y comprimido")) {
			btnDescomprimirArchivo.setEnabled(true);
		}else {
			btnDescomprimirArchivo.setEnabled(false);
		}	
	}
	
	private void EstadoBotonEliminar() {
		if(tableDatos.getSelectedRow() != -1) {
			btnEliminarArchivo.setEnabled(true);
		}else {
			btnEliminarArchivo.setEnabled(false);
		}	
	}
}
