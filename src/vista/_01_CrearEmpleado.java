package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Controlador;
import modelo.Modelo;

public class _01_CrearEmpleado extends JFrame implements Vista {

	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtEdad;
	private JTextField txtSueldo;
	private JTextField txtGenero;
	private JButton btnCrearUsuario;
	private Controlador miControlador;
	private Modelo miModelo;

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public _01_CrearEmpleado() {
		setBounds(100, 100, 453, 342);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);


		JLabel lblCrearEmpleado = new JLabel("Crear Empleado");
		lblCrearEmpleado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		lblCrearEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearEmpleado.setBounds(0, 26, 439, 43);
		getContentPane().add(lblCrearEmpleado);

		txtNombre = new JTextField();
		txtNombre.setBounds(32, 108, 96, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(174, 108, 96, 19);
		getContentPane().add(txtDni);
		txtDni.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(309, 108, 96, 19);
		getContentPane().add(txtEdad);
		txtEdad.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		txtSueldo = new JTextField();
		txtSueldo.setColumns(10);
		txtSueldo.setBounds(104, 183, 96, 19);
		getContentPane().add(txtSueldo);
		txtEdad.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		txtGenero = new JTextField();
		txtGenero.setColumns(10);
		txtGenero.setBounds(245, 183, 96, 19);
		getContentPane().add(txtGenero);
		txtGenero.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(32, 93, 96, 13);
		getContentPane().add(lblNombre);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setBounds(174, 93, 96, 13);
		getContentPane().add(lblDni);

		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdad.setBounds(309, 93, 96, 13);
		getContentPane().add(lblEdad);

		JLabel lblSueldo = new JLabel("Sueldo");
		lblSueldo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSueldo.setBounds(104, 170, 96, 13);
		getContentPane().add(lblSueldo);

		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenero.setBounds(245, 170, 96, 13);
		getContentPane().add(lblGenero);

		btnCrearUsuario = new JButton("Crear");
		btnCrearUsuario.setEnabled(false);
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cogerDatosCrearUsuario();
				limpiarCampos();
			}

		});
		btnCrearUsuario.setBounds(174, 244, 96, 21);
		getContentPane().add(btnCrearUsuario);

		JButton btnVolverMenu = new JButton("Menu");
		btnVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				miControlador.cambiarVentana(1, 0);
			}
		});
		btnVolverMenu.setBounds(0, 262, 96, 43);
		getContentPane().add(btnVolverMenu);

	}

	public void ComprobarCampos() {
		if (txtNombre.getText().length() == 0 || txtDni.getText().length() == 0 || txtEdad.getText().length() == 0
				|| txtSueldo.getText().length() == 0 || txtGenero.getText().length() == 0) {
			btnCrearUsuario.setEnabled(false);
		} else {
			btnCrearUsuario.setEnabled(true);
		}
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtDni() {
		return txtDni;
	}

	public JTextField getTxtEdad() {
		return txtEdad;
	}

	public JTextField getTxtSueldo() {
		return txtSueldo;
	}

	public JTextField getTxtGenero() {
		return txtGenero;
	}

	@Override
	public void setModelo(Modelo miModelo) {

		this.miModelo = miModelo;
	}

	private void limpiarCampos() {
		txtNombre.setText("");
		txtDni.setText("");
		txtSueldo.setText("");
		txtEdad.setText("");
		txtGenero.setText("");
		btnCrearUsuario.setEnabled(false);
	}
}
