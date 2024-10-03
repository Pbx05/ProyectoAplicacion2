package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
		lblCrearEmpleado.setForeground(new Color(255, 255, 255));
		lblCrearEmpleado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		lblCrearEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearEmpleado.setBounds(0, 26, 439, 43);
		getContentPane().add(lblCrearEmpleado);

		// textField de nombre
		txtNombre = new JTextField();
		txtNombre.setBackground(new Color(192, 192, 192));
		PlaceholderFocusListener focusListener = new PlaceholderFocusListener(txtNombre, "Nombre");
		txtNombre.addFocusListener(focusListener);
		txtNombre.setBounds(32, 108, 96, 19);
		getContentPane().add(txtNombre);
		txtNombre.setText("Nombre");
		txtNombre.addFocusListener(new PlaceholderFocusListener(txtNombre, "Titulo"));
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombre.setForeground(Color.GRAY);
		txtNombre.setBorder(null);
		txtNombre.setBorder(BorderFactory.createCompoundBorder(txtNombre.getBorder(),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		txtNombre.setColumns(10);

		txtNombre.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if ("Titulo".equals(txtNombre.getText())) {
					txtNombre.setForeground(Color.GRAY);
				} else {
					txtNombre.setForeground(Color.BLACK);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (txtNombre.getText().isEmpty()) {
					txtNombre.setForeground(Color.GRAY);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		// TextField de dni
		txtDni = new JTextField();
		txtDni.setBackground(new Color(192, 192, 192));
		focusListener = new PlaceholderFocusListener(txtDni, "Dni");
		txtDni.addFocusListener(focusListener);
		txtDni.setBounds(174, 108, 96, 19);
		getContentPane().add(txtDni);
		txtDni.setText("Dni");
		txtDni.addFocusListener(new PlaceholderFocusListener(txtDni, "Dni"));
		txtDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDni.setForeground(Color.GRAY);
		txtDni.setBorder(null);
		txtDni.setBorder(
				BorderFactory.createCompoundBorder(txtDni.getBorder(), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		txtDni.setColumns(10);

		txtDni.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if ("Titulo".equals(txtDni.getText())) {
					txtDni.setForeground(Color.GRAY);
				} else {
					txtDni.setForeground(Color.BLACK);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (txtDni.getText().isEmpty()) {
					txtDni.setForeground(Color.GRAY);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		txtDni.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		txtDni.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		// textField de edad
		txtEdad = new JTextField();
		txtEdad.setBackground(new Color(192, 192, 192));
		focusListener = new PlaceholderFocusListener(txtEdad, "Edad");
		txtEdad.addFocusListener(focusListener);
		txtEdad.setBounds(309, 108, 96, 19);
		getContentPane().add(txtEdad);
		txtEdad.setText("Edad");
		txtEdad.addFocusListener(new PlaceholderFocusListener(txtEdad, "Edad"));
		txtEdad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEdad.setForeground(Color.GRAY);
		txtEdad.setBorder(null);
		txtEdad.setBorder(
				BorderFactory.createCompoundBorder(txtEdad.getBorder(), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		txtEdad.setColumns(10);

		txtEdad.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if ("Titulo".equals(txtEdad.getText())) {
					txtEdad.setForeground(Color.GRAY);
				} else {
					txtEdad.setForeground(Color.BLACK);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (txtEdad.getText().isEmpty()) {
					txtEdad.setForeground(Color.GRAY);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		txtEdad.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		// textField de sueldo
		txtSueldo = new JTextField();
		txtSueldo.setBackground(new Color(192, 192, 192));
		focusListener = new PlaceholderFocusListener(txtSueldo, "Sueldo");
		txtSueldo.addFocusListener(focusListener);
		txtSueldo.setBounds(104, 183, 96, 19);
		getContentPane().add(txtSueldo);
		txtSueldo.setText("Sueldo");
		txtSueldo.addFocusListener(new PlaceholderFocusListener(txtSueldo, "Sueldo"));
		txtSueldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSueldo.setForeground(Color.GRAY);
		txtSueldo.setBorder(null);
		txtSueldo.setBorder(BorderFactory.createCompoundBorder(txtSueldo.getBorder(),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		txtSueldo.setColumns(10);

		txtSueldo.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if ("Titulo".equals(txtSueldo.getText())) {
					txtSueldo.setForeground(Color.GRAY);
				} else {
					txtSueldo.setForeground(Color.BLACK);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (txtSueldo.getText().isEmpty()) {
					txtSueldo.setForeground(Color.GRAY);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		txtEdad.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		// Etiqueta de genero
		txtGenero = new JTextField();
		txtGenero.setBackground(new Color(192, 192, 192));
		focusListener = new PlaceholderFocusListener(txtGenero, "Genero");
		txtGenero.addFocusListener(focusListener);
		txtGenero.setBounds(245, 183, 96, 19);
		getContentPane().add(txtGenero);
		txtGenero.setText("Genero");
		txtGenero.addFocusListener(new PlaceholderFocusListener(txtGenero, "Genero"));
		txtGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtGenero.setForeground(Color.GRAY);
		txtGenero.setBorder(null);
		txtGenero.setBorder(BorderFactory.createCompoundBorder(txtGenero.getBorder(),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		txtGenero.setColumns(10);

		txtGenero.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if ("Titulo".equals(txtGenero.getText())) {
					txtGenero.setForeground(Color.GRAY);
				} else {
					txtGenero.setForeground(Color.BLACK);
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (txtGenero.getText().isEmpty()) {
					txtGenero.setForeground(Color.GRAY);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		txtGenero.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		btnCrearUsuario = new JButton("Crear");
		btnCrearUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCrearUsuario.setEnabled(false);
		btnCrearUsuario.setForeground(new Color(255, 255, 255));
		btnCrearUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCrearUsuario.setBackground(new Color(70, 70, 70));
				btnCrearUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCrearUsuario.setBackground(new Color(0, 0, 0));
			}
		});
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.cogerDatosCrearUsuario();
				limpiarCampos();
			}
		});
		btnCrearUsuario.setBackground(new Color(0, 0, 0));
		btnCrearUsuario.setBorder(null);

		btnCrearUsuario.setBounds(174, 244, 96, 21);
		getContentPane().add(btnCrearUsuario);

		JButton btnVolverMenu = new JButton("Menu");
		btnVolverMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVolverMenu.setForeground(new Color(0, 0, 0));
		btnVolverMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVolverMenu.setBackground(new Color(200, 200, 200));
				btnVolverMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnVolverMenu.setBackground(new Color(255, 255, 255));
			}
		});
		btnVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				miControlador.cambiarVentana(1, 0);
			}
		});
		btnVolverMenu.setBackground(new Color( 255, 255, 255));
		btnVolverMenu.setBorder(null);

		btnVolverMenu.setBounds(0, 262, 96, 43);
		getContentPane().add(btnVolverMenu);

		JLabel lblFondoVelocidad = new JLabel("");
		lblFondoVelocidad.setBounds(0, 0, 439, 305);
		getContentPane().add(lblFondoVelocidad);
		ImageIcon imagenVelocidad = new ImageIcon("./src/Assets/Velocidad.jpg");
		Image imagenEscalada = imagenVelocidad.getImage().getScaledInstance(lblFondoVelocidad.getWidth(),
				lblFondoVelocidad.getHeight(), Image.SCALE_SMOOTH);
		lblFondoVelocidad.setIcon(new ImageIcon(imagenEscalada));

	}

	public void ComprobarCampos() {
		if ((txtNombre.getText().length() == 0 || txtNombre.getText().equals("Nombre"))
				|| (txtDni.getText().length() == 0 || txtDni.getText().equals("Dni"))
				|| (txtEdad.getText().length() == 0 || txtEdad.getText().equals("Edad"))
				|| (txtSueldo.getText().length() == 0 || txtSueldo.getText().equals("Sueldo"))
				|| (txtGenero.getText().length() == 0 || txtGenero.getText().equals("Genero"))) {
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
		txtNombre.setText("Nombre");
		txtDni.setText("Dni");
		txtSueldo.setText("Sueldo");
		txtEdad.setText("Edad");
		txtGenero.setText("Genero");
		btnCrearUsuario.setEnabled(false);
	}
}

class PlaceholderFocusListener implements FocusListener {
	private final JTextField field;
	private final String placeholder;

	public PlaceholderFocusListener(JTextField field, String placeholder) {
		this.field = field;
		this.placeholder = placeholder;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (field.getText().equals(placeholder)) {
			field.setText("");
			if (field instanceof JPasswordField) {
				((JPasswordField) field).setEchoChar('*'); // Para ocultar los caracteres al escribir
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (field.getText().isEmpty()) {
			field.setText(placeholder);
			if (field instanceof JPasswordField) {
				((JPasswordField) field).setEchoChar((char) 0); // Para que el texto sea visible
			}
		}
	}
}
