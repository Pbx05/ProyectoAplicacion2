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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

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
	private JLabel lblMensajeDatoIncorrecto;
	private JLabel lblMensajeDatoCorrecto;

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public _01_CrearEmpleado() {
		setBounds(100, 100, 453, 342);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Etiqueta para declarar la función de la vista
		JLabel lblCrearEmpleado = new JLabel("Crear Empleado");
		// Color blanco para que se vea con la imagen que he añadido
		lblCrearEmpleado.setForeground(new Color(255, 255, 255));
		// Fuente y tamaño acorde con una titulación
		lblCrearEmpleado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 29));
		// Coloco las letras en el centro de la etiqueta
		lblCrearEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		// Le doy una posición en la vista
		lblCrearEmpleado.setBounds(0, 26, 439, 43);
		// Lo añado al contentPane
		getContentPane().add(lblCrearEmpleado);

		// textField de nombre
		txtNombre = new JTextField();
		// Le doy un clor al textField gris
		txtNombre.setBackground(new Color(192, 192, 192));
		// Hago un objeto de la clase PlaceFocusListener
		PlaceholderFocusListener focusListener = new PlaceholderFocusListener(txtNombre, "Nombre");
		// Le añado mi objeto focusListener al textField de nombre
		txtNombre.addFocusListener(focusListener);
		
		lblMensajeDatoIncorrecto = new JLabel("Tipo de dato incorrecto");
		lblMensajeDatoIncorrecto.setEnabled(false);
		lblMensajeDatoIncorrecto.setVisible(false);
		lblMensajeDatoIncorrecto.setForeground(new Color(255, 255, 255));
		lblMensajeDatoIncorrecto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajeDatoIncorrecto.setBounds(104, 279, 237, 13);
		getContentPane().add(lblMensajeDatoIncorrecto);
		// Lo coloco en una posición de la vista
		txtNombre.setBounds(32, 108, 96, 19);
		// Lo añado al contentPane
		getContentPane().add(txtNombre);
		// Le establezco el texto que aparecerá en el textField
		txtNombre.setText("Nombre");
		// Le añado un FocusListener personalizado, y le indico que en txtNombre
		// aparezca el texto de ayuda "titulo" cuando el campo de texto esté vacío y no
		// tenga foco
		txtNombre.addFocusListener(new PlaceholderFocusListener(txtNombre, "Nombre"));
		// Le añado una fuente y un tamaño
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		// Le establezco el color de las letras a un gris
		txtNombre.setForeground(Color.GRAY);
		// Le quito el borde para darle uno personalizado
		txtNombre.setBorder(null);
		// Con margen interno de 10 pixels a la izquierda para que haya espacio entre el
		// texto y el borde del campo
		txtNombre.setBorder(BorderFactory.createCompoundBorder(txtNombre.getBorder(),
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		// El campo del texto tendrá 10 columnas
		txtNombre.setColumns(10);

		// El document listener escucha los cambios ene l contenido del campo, cuando se
		// escribe o se elimina
		txtNombre.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			// Cuando se introduce texto, se comprueba si el texto introducido es
			// Nombre(texto de ayuda) y si devuelve true, establece el color de las letras a
			// gris, si devuelve false deja el color negro para que sepa el ususario que
			// está escribiendo
			public void insertUpdate(DocumentEvent e) {
				if ("Nombre".equals(txtNombre.getText())) {
					txtNombre.setForeground(Color.GRAY);
				} else {
					txtNombre.setForeground(Color.BLACK);
				}
			}

			@Override
			// Cuando se borra texto, comprueba si el textField está vacío, y si devuevle
			// true, establece el color de las letras a gris, ya que si está vacío, me va a
			// aparecer el texto de ayuda
			public void removeUpdate(DocumentEvent e) {
				if (txtNombre.getText().isEmpty()) {
					txtNombre.setForeground(Color.GRAY);
				}
			}

			// No utilizo este método pero debe estar implementado
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		// Esto se activa cuando el usuario pulsa una tecla y llama al método comprobar
		// campos
		txtNombre.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ComprobarCampos();
			}

		});

		// TextField de dni
		// Funciona igual que el textField de Nombre, que ya he comentado entero, lo
		// unico que cambia es la palabra clave, que será Dni en lugar de Nombre, así
		// que no lo comento dado que su explicación ya ha quedado clara en el bloque de
		// código superior
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
				if ("Dni".equals(txtDni.getText())) {
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
		// Funciona igual que el textField de Nombre, que ya he comentado entero, lo
		// unico que cambia es la palabra clave, que será Edad en lugar de Nombre, así
		// que no lo comento dado que su explicación ya ha quedado clara en el bloque de
		// código superior
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
				if ("Edad".equals(txtEdad.getText())) {
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
		// Funciona igual que el textField de Nombre, que ya he comentado entero, lo
		// unico que cambia es la palabra clave, que será Sueldo en lugar de Nombre, así
		// que no lo comento dado que su explicación ya ha quedado clara en el bloque de
		// código superior
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
				if ("Sueldo".equals(txtSueldo.getText())) {
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
		// Funciona igual que el textField de Nombre, que ya he comentado entero, lo
		// unico que cambia es la palabra clave, que será Genero en lugar de Nombre, así
		// que no lo comento dado que su explicación ya ha quedado clara en el bloque de
		// código superior
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
				if ("Genero".equals(txtGenero.getText())) {
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

		// Creo un JButton para introducir un nuevo archivo en la carpeta
		btnCrearUsuario = new JButton("Crear");
		// Establezco la fuente, el tamaño y el estilo, para darle estética al botón
		btnCrearUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		// Lo establezco en false para que no pueda pulsarse dejando campos vacíos
		btnCrearUsuario.setEnabled(false);
		// Le doy un color blanco a las letras, dado que el botón será de color negro
		btnCrearUsuario.setForeground(new Color(255, 255, 255));
		// Le añado un mouseListener al botón, para que pueda interactuar el botón,
		// según que
		// acciones tome el usuario con el ratón
		btnCrearUsuario.addMouseListener(new MouseAdapter() {
			@Override
			// Este método detecta cuando el ratón entra en la zona del botón y aplica
			// ciertos comandos
			public void mouseEntered(MouseEvent e) {
				// Cambia el color a un gris, subiendole un poco los pigmentos, de 0 a 70
				btnCrearUsuario.setBackground(new Color(70, 70, 70));
				// Cambiar el icono del ratón, de una flecha, a la mano de selección de objetos
				btnCrearUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		// Le añado un mouseListener al contentPane, para cada vez que se salga del
		// botón, y el ratón se sitúe encima del contentPAne
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Vuelvo a establacer el color del botón al negro original
				btnCrearUsuario.setBackground(new Color(0, 0, 0));
			}
		});
		// Le añado un actionListener al botón, para que ejecute una acción al ser
		// pulsado
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					// LLamo al controlador para recoger los datos de los textFields, para empezar a
					// operar con ellos
					if(miControlador.cogerDatosCrearUsuario()) {
						// Limpio los campos por si el usuario decide introducir mas de un registro
						limpiarCampos();
						lblMensajeDatoIncorrecto.setVisible(false);
						lblMensajeDatoCorrecto.setVisible(true);
						
					}else {
						lblMensajeDatoIncorrecto.setVisible(true);
						lblMensajeDatoCorrecto.setVisible(false);
						
					}
				
			}
		});

		// Le establezo color al fondo del botón, en este caso un negro para el
		// contraste con sus letras y con el fondo
		btnCrearUsuario.setBackground(new Color(0, 0, 0));
		// Le quito el borde al boton
		btnCrearUsuario.setBorder(null);

		// Lo coloco en una posición concreta del contentPane
		btnCrearUsuario.setBounds(174, 244, 96, 21);
		// Añado el botón al contentPane
		getContentPane().add(btnCrearUsuario);

		// Creo un JButton para volver a la vista inicial
		JButton btnVolverMenu = new JButton("Menu");
		// Le doy una fuente, un tamaño y un estilo
		btnVolverMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		// Al contrario que el otro botón, le doy un color negro a las letras, para
		// contraste
		btnVolverMenu.setForeground(new Color(0, 0, 0));
		// Añado un mouseListener al botón
		btnVolverMenu.addMouseListener(new MouseAdapter() {
			@Override
			// Cada vez que el ratón entra en la zona del botón se ejecutan los siguientes
			// comandos
			public void mouseEntered(MouseEvent e) {
				// Le cambio el color al fondo a uno mas oscuro
				btnVolverMenu.setBackground(new Color(200, 200, 200));
				// Cambio el icono del ratón a la mano de seleccion
				btnVolverMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		// Añado un mouseListener al contentPane
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			// Cada vez que el raton sale del botón, establece otra vez el color del botón a
			// su color original, blanco en este caso
			public void mouseEntered(MouseEvent e) {
				btnVolverMenu.setBackground(new Color(255, 255, 255));
			}
		});
		// Añado un actionListener al botón de volver al menú para que se pueda volver a
		// la vista original
		btnVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Método para restablecer los campos, para que no se queden con contenido si se
				// sale y se vuelve a entrar en la vista
				limpiarCampos();
				miControlador.cambiarVentana(1, 0);
				
			}
		});
		// Le doy color al fondo del botón, el color con más brillo
		btnVolverMenu.setBackground(new Color(255, 255, 255));
		// Le quiero quitar los borde al boton
		btnVolverMenu.setBorder(null);

		// Le doy una posición en el content pane
		btnVolverMenu.setBounds(0, 262, 96, 43);
		// Lo añado al contentPAne
		getContentPane().add(btnVolverMenu);
		
		lblMensajeDatoCorrecto = new JLabel("Empleado creado con éxito");
		lblMensajeDatoCorrecto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajeDatoCorrecto.setForeground(new Color(255, 255, 255));
		lblMensajeDatoCorrecto.setEnabled(false);
		lblMensajeDatoCorrecto.setVisible(false);
		lblMensajeDatoCorrecto.setBounds(104, 279, 237, 13);
		getContentPane().add(lblMensajeDatoCorrecto);

		// Una label que contendrá la imagen que se pone de fondo de la vista, le quito
		// el contenido del texto
		JLabel lblFondoVelocidad = new JLabel("");
		// Lo establezco ocupando toda la vista
		lblFondoVelocidad.setBounds(0, 0, 439, 305);
		// Lo añado al conentpane
		getContentPane().add(lblFondoVelocidad);
		// Creo un imageIcon y le paso la ruta a nuestra carpeta de assets con la imagen
		// concreta que quiero añadir
		ImageIcon imagenVelocidad = new ImageIcon("./src/Assets/Velocidad.jpg");
		// Para que la imagen se adapte a las dimensiones del label, creo un objeto
		// Image y le aplico las necesidades de tamaño
		Image imagenEscalada = imagenVelocidad.getImage().getScaledInstance(lblFondoVelocidad.getWidth(),
				lblFondoVelocidad.getHeight(), Image.SCALE_SMOOTH);
		// Con el setIcon establezco a la label la imagen escalada
		lblFondoVelocidad.setIcon(new ImageIcon(imagenEscalada));

	}

	// Método para autorizar el botón o bloquearlo, compruba si alguno de los
	// textFields está vacío o si contienen la palabra clave designada a la ayuda
	// del sentido del txtField
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

	// Getters de los textFields
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
	// Establezco el objeto del modelo
	public void setModelo(Modelo miModelo) {

		this.miModelo = miModelo;
	}

	// Método que establece el texto de cada campo a su texto de ayuda identificador
	// de campo
	private void limpiarCampos() {
		txtNombre.setText("Nombre");
		txtDni.setText("Dni");
		txtSueldo.setText("Sueldo");
		txtEdad.setText("Edad");
		txtGenero.setText("Genero");
		// Establezco el botón de crear en inhabilitado
		btnCrearUsuario.setEnabled(false);
	}
}

// He creado la clase PlaceholderFOcusListener, que implementa la interfaz FOcusListener, una clase que uso en el código para generar el texto temporal en los textFields
class PlaceholderFocusListener implements FocusListener {
	// Creo un atributo JtextField, que será una referencia al textFIeld al que se
	// aplicará el comportamiento del placeHolder
	private final JTextField field;
	// VAriable String que contendra el texto de ayuda que aparecerá en cada
	// textField
	private final String placeholder;

	// Constructor normal
	public PlaceholderFocusListener(JTextField field, String placeholder) {
		this.field = field;
		this.placeholder = placeholder;
	}

	@Override
	// Este método se activa cuando el textField es usado, cuando tiene el foco
	public void focusGained(FocusEvent e) {
		// Comprueba si el contenido del field que se ha seleccionado, es igual al texto
		// designado como ayuda por el placeHolder, en el caso de que así sea, eliminará
		// los caracteres que había de ayuda para poder escribir el nuevo contenido
		if (field.getText().equals(placeholder)) {
			field.setText("");
		}
	}

	@Override
	// Este método se activa cuando el TextField se deselecciona, cuando pierde el
	// foco
	public void focusLost(FocusEvent e) {
		// Comprueba si el TextField está vacío, en caso de que devuelva true, establece
		// el text del textField al texto guardado en la variable placeHolder
		if (field.getText().isEmpty()) {
			field.setText(placeholder);
		}
	}
}
