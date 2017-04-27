package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;

import Logic.dataConnection;
import Logic.institutoMontenegro;

import javax.swing.SwingUtilities;

public class crearEstudiante extends javax.swing.JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabelTitulo;
	private JButton jButtonCrearEstudiante;
	private JLabel jLabelTipoPoblacion;
	private JLabel jLabelDocumento;
	private JLabel jLabelSexo;
	private JLabel jLabelMetodologia;
	private JTextField jTextFieldGrado;
	private JLabel jLabelGrado;
	private JTextField jTextFieldDocumento;
	private JTextField jTextFieldApellidos;
	private JLabel jLabelApellidos;
	private JTextField jTextFieldnombre;
	private JLabel jLabelnombre;
	private JButton jButtonAtras;
	private JButton jButtonGuardarFotos;
	private JButton jButtonAtras2;
	private JButton jButtonTomarFoto;
	private JPanel jPanel1;
	private JButton jButtonTerminar;
	private JComboBox<String> jComboMetodologia;
	private JComboBox<String> jComboSexo;
	private JComboBox<String> jComboTipoPoblacion;
	institutoMontenegro instituto = new institutoMontenegro();

	// atributos necesarios para guardar imagenes en la base de datos
	private DaemonThread myThread = new DaemonThread();
	int count = 0;
	// Variable de incremento
	int cont = 0;
	VideoCapture webSource = null;
	private Mat frame = new Mat();
	MatOfByte mem = new MatOfByte();
	CascadeClassifier faceDetector = new CascadeClassifier(
			crearEstudiante.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
	MatOfRect faceDetections = new MatOfRect();

	private String[] genero = { "FEMENINO", "MASCULINO" };;
	private String[] metodologias = { "TRADICIONAL", "FLEXIBLE" };
	private String[] tipoPoblacion = { "AFRO COLOMBIANO", "DESPLAZADOS", "INDIGENA", "OTRA", "N/A" };

	// atributos para el manejo de la base de datos
	PreparedStatement pst;
	Connection cn;
	ResultSet result;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(crearEstudiante.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(crearEstudiante.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(crearEstudiante.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(crearEstudiante.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				crearEstudiante inst = new crearEstudiante();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	class DaemonThread implements Runnable {

		protected volatile boolean runnable = false;

		public void run() {
			synchronized (this) {
				while (runnable) {
					if (webSource.grab()) {
						try {
							webSource.retrieve(frame);
							Graphics g = jPanel1.getGraphics();
							faceDetector.detectMultiScale(frame, faceDetections);
							for (Rect rect : faceDetections.toArray()) {
								// System.out.println("ttt");
								Core.rectangle(frame, new Point(rect.x, rect.y),
										new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
							}
							Highgui.imencode(".bmp", frame, mem);
							Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
							BufferedImage buff = (BufferedImage) im;
							if (g.drawImage(buff, 0, 0, getWidth(), getHeight() - 150, 0, 0, buff.getWidth(),
									buff.getHeight(), null)) {
								if (runnable == false) {
									System.out.println("Paused ..... ");
									this.wait();
								}
							}
						} catch (Exception ex) {
							System.out.println("Error");
						}
					}
				}
			}
		}

	}

	public crearEstudiante() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabelTitulo = new JLabel();
				getContentPane().add(jLabelTitulo);
				jLabelTitulo.setText("Gestionar Estudiante ");
				jLabelTitulo.setBounds(189, 30, 144, 27);
			}
			{
				jLabelnombre = new JLabel();
				getContentPane().add(jLabelnombre);
				jLabelnombre.setText("NOMBRE:");
				jLabelnombre.setBounds(38, 97, 92, 16);
			}
			{
				jTextFieldnombre = new JTextField();
				getContentPane().add(jTextFieldnombre);
				jTextFieldnombre.setBounds(189, 90, 160, 23);
			}
			{
				jLabelApellidos = new JLabel();
				getContentPane().add(jLabelApellidos);
				jLabelApellidos.setText("APELLIDOS");
				jLabelApellidos.setBounds(38, 135, 92, 16);
			}
			{
				jTextFieldApellidos = new JTextField();
				getContentPane().add(jTextFieldApellidos);
				jTextFieldApellidos.setBounds(189, 132, 160, 23);
			}
			{
				jLabelDocumento = new JLabel();
				getContentPane().add(jLabelDocumento);
				jLabelDocumento.setText("Documento Identidad");
				jLabelDocumento.setBounds(38, 181, 151, 16);
			}
			{
				jTextFieldDocumento = new JTextField();
				getContentPane().add(jTextFieldDocumento);
				jTextFieldDocumento.setBounds(189, 178, 160, 23);
			}
			{
				jLabelGrado = new JLabel();
				getContentPane().add(jLabelGrado);
				jLabelGrado.setText("GRADO");
				jLabelGrado.setBounds(38, 232, 107, 16);
			}
			{
				jTextFieldGrado = new JTextField();
				getContentPane().add(jTextFieldGrado);
				jTextFieldGrado.setBounds(189, 229, 160, 23);
			}
			{
				jLabelSexo = new JLabel();
				getContentPane().add(jLabelSexo);
				jLabelSexo.setText("SEXO");
				jLabelSexo.setBounds(38, 270, 116, 16);
			}

			{
				jLabelMetodologia = new JLabel();
				getContentPane().add(jLabelMetodologia);
				jLabelMetodologia.setText("METODOLOGIA");
				jLabelMetodologia.setBounds(38, 309, 84, 16);
			}

			{
				jComboMetodologia = new JComboBox<>(metodologias);
				getContentPane().add(jComboMetodologia);
				jComboMetodologia.setBounds(189, 306, 160, 23);
			}
			{
				jLabelTipoPoblacion = new JLabel();
				getContentPane().add(jLabelTipoPoblacion);
				jLabelTipoPoblacion.setText("TIPO POBLACION");
				jLabelTipoPoblacion.setBounds(38, 345, 151, 16);
			}

			{
				jComboTipoPoblacion = new JComboBox<>(tipoPoblacion);
				getContentPane().add(jComboTipoPoblacion);
				jComboTipoPoblacion.setBounds(189, 342, 160, 23);
			}
			{
				jButtonCrearEstudiante = new JButton();
				getContentPane().add(jButtonCrearEstudiante);
				jButtonCrearEstudiante.setText("Crear Estudiante");
				jButtonCrearEstudiante.setBounds(384, 201, 142, 28);
				jButtonCrearEstudiante.addActionListener(this);
			}
			{
				jComboSexo = new JComboBox<>(genero);
				getContentPane().add(jComboSexo);
				// jComboSexo.setBounds(208, 302, 160, 23);
				jComboSexo.setBounds(189, 267, 160, 23);
			}
			{
				jButtonAtras = new JButton();
				getContentPane().add(jButtonAtras);
				jButtonAtras.setText("Atras");
				jButtonAtras.setBounds(450, 32, 41, 23);
				jButtonAtras.addActionListener(this);
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setBounds(560, 12, 678, 240);
			}
			{
				jButtonTomarFoto = new JButton();
				getContentPane().add(jButtonTomarFoto);
				jButtonTomarFoto.setText("Tomar Fotos");
				jButtonTomarFoto.setBounds(384, 146, 142, 26);
				jButtonTomarFoto.addActionListener(this);
				jButtonTomarFoto.setVisible(false);
			}
			{
				jButtonAtras2 = new JButton();
				getContentPane().add(jButtonAtras2);
				jButtonAtras2.setText("Atras");
				jButtonAtras2.setBounds(1182, 357, 87, 33);
				jButtonAtras2.addActionListener(this);
			}
			{
				jButtonGuardarFotos = new JButton();
				getContentPane().add(jButtonGuardarFotos);
				jButtonGuardarFotos.setText("Guardar Fotos");
				jButtonGuardarFotos.setBounds(843, 270, 122, 28);
				jButtonGuardarFotos.addActionListener(this);
			}
			{
				jButtonTerminar = new JButton();
				getContentPane().add(jButtonTerminar);
				jButtonTerminar.setText("Nuevo Estudiante");
				jButtonTerminar.setBounds(818, 345, 178, 33);
				jButtonTerminar.addActionListener(this);
			}
			pack();
			// this.setSize(725, 424);
			this.setSize(549, 424);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButtonAtras || e.getSource() == jButtonAtras2) {
			principalAdministrador p = new principalAdministrador();
			p.setVisible(true);
			this.dispose();
		}

		if (e.getSource() == jButtonTomarFoto) {
			setCampos();
			this.setSize(1300, 430);
			jButtonAtras.setVisible(false);
			jButtonTomarFoto.setVisible(false);
			jButtonCrearEstudiante.setVisible(false);
			webSource = new VideoCapture(0); // video capture from default cam
			myThread = new DaemonThread(); // create object of threat class
			Thread t = new Thread(myThread);
			t.setDaemon(true);
			myThread.runnable = true;
			t.start(); // start thread
		}
		if (e.getSource() == jButtonGuardarFotos) {
			myThread.runnable = true;
			String nombre = jTextFieldnombre.getText();
			String apellido = jTextFieldApellidos.getText();
			int documentoEstudiante = Integer.parseInt(jTextFieldDocumento.getText());

			try {
				cn = dataConnection.conexion();

				pst = (PreparedStatement) cn
						.prepareStatement("INSERT INTO imagen(documentoEstudiante,nombre,foto) VALUES(?,?,?)");

				// convertir la imagen capturada y guardarla en un archivo
				// jpg
				convertir(frame);

				pst.setInt(1, documentoEstudiante);
				pst.setString(2, "Foto" + "_" + apellido + "_" + nombre);
				pst.setLong(3, frame.nativeObj);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Foto Registrada");

			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "error");
			} catch (FileNotFoundException ex) {
				Logger.getLogger(crearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(crearEstudiante.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		if (e.getSource() == jButtonTerminar) {
			limpiar();

			myThread.runnable = false;
			this.setSize(549, 424);
			jButtonAtras.setVisible(true);
			jButtonTomarFoto.setVisible(false);
			jTextFieldApellidos.setEditable(true);
			jTextFieldnombre.setEditable(true);
			jTextFieldDocumento.setEditable(true);
			jTextFieldGrado.setEditable(true);
			jComboMetodologia.setEditable(true);
			jComboSexo.setEditable(true);
			jComboTipoPoblacion.setEditable(true);
			jButtonCrearEstudiante.setVisible(true);

		}
		if (e.getSource() == jButtonCrearEstudiante) {
			String nombres = jTextFieldnombre.getText();
			String apellidos = jTextFieldApellidos.getText();
			int documento = Integer.parseInt(jTextFieldDocumento.getText());
			String grado = jTextFieldGrado.getText();
			String sexo = sexoF((String) jComboSexo.getSelectedItem());
			String tipoPoblacion = (String) jComboTipoPoblacion.getSelectedItem();
			String metodologia = (String) jComboMetodologia.getSelectedItem();

			// Validar que ingrese los campos obligatorios para registrarlo en
			// la base de datos
			if (nombres.length() != 0 && apellidos.length() != 0 && documento != 0 && grado.length() != 0) {
				cn = dataConnection.conexion();
				try {
					pst = cn.prepareStatement("insert into estudiante (documento,nombres,apellidos,"
							+ "grado,sexo,tipoPoblacion,modeloPedagogico) values (?,?,?,?,?,?,?)");

					pst.setInt(1, documento);
					pst.setString(2, nombres);
					pst.setString(3, apellidos);
					pst.setString(4, grado);
					pst.setString(5, sexo);
					pst.setString(6, tipoPoblacion);
					pst.setString(7, metodologia);

					int res = pst.executeUpdate();
					if (res > 0) {
						Date fecha = fechaIncio();
						try {
							instituto.insertarRegistro(documento, fecha, fecha);
						} catch (ParseException e1) {

							e1.printStackTrace();
						}

						JOptionPane.showMessageDialog(null, "El estudiante se ha agregado con exito");
						setCampos();
						jButtonTomarFoto.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "ups...ocurrio un problema");
					}
					cn.close();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		}

	}

	public void limpiar() {
		jTextFieldApellidos.setText(" ");
		jTextFieldnombre.setText(" ");
		jTextFieldDocumento.setText(" ");
		jTextFieldGrado.setText(" ");
	}

	public Date fechaIncio() {
		String date1 = "1999-05-24";
		Date fecha = java.sql.Date.valueOf(date1);
		return fecha;
	}

	public void setCampos() {
		jTextFieldApellidos.setEditable(false);
		jTextFieldnombre.setEditable(false);
		jTextFieldDocumento.setEditable(false);
		jTextFieldGrado.setEditable(false);
		jComboMetodologia.setEditable(false);
		jComboSexo.setEditable(false);
		jComboTipoPoblacion.setEditable(false);
	}

	/**
	 * Metodo que permite guardar la imagen en un archivo jpg
	 * 
	 * @param imagen,
	 *            imagen a guardar
	 * @throws IOException
	 */
	private void convertir(Mat imagen) throws IOException {
		MatOfByte matOfByte = new MatOfByte();
		Highgui.imencode(".jpg", imagen, matOfByte);

		byte[] byteArray = matOfByte.toArray();
		int alto = 200;
		int ancho = 200;
		BufferedImage bufImage = new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_RGB);
		;

		try {

			InputStream in = new ByteArrayInputStream(byteArray);
			bufImage = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file = new File("imagenes/foto" + cont + ".png");
		ImageIO.write((RenderedImage) (Image) bufImage, "png", file);
		cont += 1;
	}

	/**
	 * Metodo que permite validar si el char ingresado es numero
	 * 
	 * @param c
	 * @return flag
	 */
	private boolean esNumero(char c) {
		if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9'
				|| c == '0') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo que permite validar que los campos esten llenos
	 */
	public boolean validarCamposObligatorios(JTextField documento, JTextField nombre, JTextField apellidos,
			JTextField grado) {

		return false;
	}

	/**
	 * Metodo que permite validar que el documento sea de 10 caracteres
	 * 
	 * @param documento
	 * @return
	 */
	public boolean validarDocumento(String documento) {
		boolean resultado = false;

		if (documento.length() <= 11) {
			for (int i = 0; i < documento.length(); i++) {
				if (esNumero(documento.charAt(i))) {
					resultado = true;
				} else {
					return false;
				}
			}

		}
		return resultado;
	}

	public String sexoF(String sexo) {
		if (sexo == "FEMENINO") {
			return "F";
		} else {
			return "M";
		}
	}

}
