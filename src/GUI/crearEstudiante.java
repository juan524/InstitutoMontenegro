package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.WindowConstants;

import Logic.dataConnection;
import Logic.institutoMontenegro;

import javax.swing.SwingUtilities;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class crearEstudiante extends javax.swing.JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabelTitulo;
	private JButton jButtonCrearEstudiante;
	private JTextField jTextFieldTipoPoblacion;
	private JLabel jLabelTipoPoblacion;
	private JLabel jLabelDocumento;
	private JLabel jLabelSexo;
	private JTextField jTextFieldMetodologia;
	private JLabel jLabelMetodologia;
	private JTextField jTextFieldSexo;
	private JTextField jTextFieldGrado;
	private JLabel jLabelGrado;
	private JTextField jTextFieldDocumento;
	private JTextField jTextFieldApellidos;
	private JLabel jLabelApellidos;
	private JTextField jTextFieldnombre;
	private JLabel jLabelnombre;
	private JButton jButtonAtras;
	institutoMontenegro instituto = new institutoMontenegro();

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				crearEstudiante inst = new crearEstudiante();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	PreparedStatement pst;
	Connection cn;
	ResultSet result;

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
				jTextFieldSexo = new JTextField();
				getContentPane().add(jTextFieldSexo);
				jTextFieldSexo.setBounds(189, 267, 160, 23);
			}
			{
				jLabelMetodologia = new JLabel();
				getContentPane().add(jLabelMetodologia);
				jLabelMetodologia.setText("METODOLOGIA");
				jLabelMetodologia.setBounds(38, 309, 84, 16);
			}
			{
				jTextFieldMetodologia = new JTextField();
				getContentPane().add(jTextFieldMetodologia);
				jTextFieldMetodologia.setBounds(189, 306, 160, 23);
			}
			{
				jLabelTipoPoblacion = new JLabel();
				getContentPane().add(jLabelTipoPoblacion);
				jLabelTipoPoblacion.setText("TIPO POBLACION");
				jLabelTipoPoblacion.setBounds(38, 345, 151, 16);
			}
			{
				jTextFieldTipoPoblacion = new JTextField();
				getContentPane().add(jTextFieldTipoPoblacion);
				jTextFieldTipoPoblacion.setBounds(189, 342, 160, 23);
			}
			{
				jButtonCrearEstudiante = new JButton();
				getContentPane().add(jButtonCrearEstudiante);
				jButtonCrearEstudiante.setText("Crear Estudiante");
				jButtonCrearEstudiante.setBounds(384, 206, 100, 23);
				jButtonCrearEstudiante.addActionListener(this);
			}
			{
				jButtonAtras = new JButton();
				getContentPane().add(jButtonAtras);
				jButtonAtras.setText("Atras");
				jButtonAtras.setBounds(451, 30, 41, 23);
				jButtonAtras.addActionListener(this);
			}
			pack();
			this.setSize(534, 421);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButtonAtras) {
			principalAdministrador p = new principalAdministrador();
			p.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == jButtonCrearEstudiante) {
			String nombres = jTextFieldnombre.getText();
			String apellidos = jTextFieldApellidos.getText();
			int documento = Integer.parseInt(jTextFieldDocumento.getText());
			String grado = jTextFieldGrado.getText();
			String sexo = jTextFieldSexo.getText();
			String tipoPoblacion = jTextFieldTipoPoblacion.getText();
			String metodologia = jTextFieldMetodologia.getText();

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
					limpiar();
				} else {
					JOptionPane.showMessageDialog(null, "ups...ocurrio un problema");
				}
				cn.close();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}

	}

	public void limpiar() {
		jTextFieldApellidos.setText(" ");
		jTextFieldnombre.setText(" ");
		jTextFieldDocumento.setText(" ");
		jTextFieldGrado.setText(" ");
		jTextFieldMetodologia.setText(" ");
		jTextFieldSexo.setText(" ");
		jTextFieldTipoPoblacion.setText(" ");
	}

	public Date fechaIncio() {
		String date1 = "1999-05-24";
		Date fecha = java.sql.Date.valueOf(date1);
		return fecha;
	}

}
