package vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Modelo;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class _00_Menu extends JFrame implements Vista{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador miControlador;
	private Modelo miModelo;
	private JTable table;
	private JLabel lblImagenPrincipio;
	private JTextField txtNombreArchivo;
	
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	@Override
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public _00_Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(317, 0, 382, 433);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Gestor de archivos avanzado");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 307, 18);
		contentPane.add(lblNewLabel);
		
		txtNombreArchivo = new JTextField();
		txtNombreArchivo.setBounds(10, 55, 96, 20);
		contentPane.add(txtNombreArchivo);
		txtNombreArchivo.setColumns(10);
		
		JButton btnAgregarArchivo = new JButton("Agregar Archivo");
		btnAgregarArchivo.setBounds(10, 145, 155, 23);
		contentPane.add(btnAgregarArchivo);
		
		JButton btnDeserializarArchivo = new JButton("Deserializar Archivo");
		btnDeserializarArchivo.setBounds(10, 179, 155, 23);
		contentPane.add(btnDeserializarArchivo);
		
		JButton btnComprimirArchivo = new JButton("Comprimir archivo");
		btnComprimirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnComprimirArchivo.setBounds(10, 214, 155, 23);
		contentPane.add(btnComprimirArchivo);
		
		JButton btnDescomprimirArchivo = new JButton("Descomprimir archivo");
		btnDescomprimirArchivo.setBounds(10, 248, 155, 23);
		contentPane.add(btnDescomprimirArchivo);
		
		JButton btnEliminarArchivo = new JButton("Eliminar archivo");
		btnEliminarArchivo.setBounds(10, 282, 155, 23);
		contentPane.add(btnEliminarArchivo);
		
		// Label para poder poner una imagen de fondo
//		lblImagenPrincipio = new JLabel("New label");
//		lblImagenPrincipio.setBounds(-36, -45, 367, 328);
//		contentPane.add(lblImagenPrincipio);
//		lblImagenPrincipio.setIcon(new ImageIcon(_00_Menu.class.getResource("/Assets/pngtree-colorful-tetris-lego-blo.jpg")));
	}
}
