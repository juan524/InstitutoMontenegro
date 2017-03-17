package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.WindowConstants;

import Logic.dataConnection;

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
public class buscarEstudiante extends javax.swing.JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabelTitulo;
	private JButton jButtonBuscar;
	private JTextField jTextFieldMetodologia;
	private JLabel jLabelMetodologia;
	private JTextField jTextFieldTipoPoblacion;
	private JLabel jLabelSexo;
	private JLabel jLabelTipoPoblacion;
	private JTextField jTextFieldSexo;
	private JTextField jTextFieldGrado;
	private JLabel jLabelGrado;
	private JTextField jTextFieldApellidos;
	private JLabel jLabelApellidos;
	private JTextField jTextFieldnombres;
	private JLabel jLabelNombres;
	private JTextField jTextFieldDocumento;
	private JLabel jLabeldocumento;
	private JLabel jLabelIntro;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				buscarEstudiante inst = new buscarEstudiante();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	PreparedStatement pst;
	Connection cn;
	ResultSet res;

	public buscarEstudiante() {
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
				jLabelTitulo.setText("BuscarEstudiante");
				jLabelTitulo.setBounds(144, 28, 146, 30);
			}
			{
				jLabelIntro = new JLabel();
				getContentPane().add(jLabelIntro);
				jLabelIntro.setText("Para buscar un estudiante ingrese el documento");
				jLabelIntro.setBounds(58, 64, 268, 28);
			}
			{
				jLabeldocumento = new JLabel();
				getContentPane().add(jLabeldocumento);
				jLabeldocumento.setText("Documento");
				jLabeldocumento.setBounds(31, 104, 88, 24);
			}
			{
				jTextFieldDocumento = new JTextField();
				getContentPane().add(jTextFieldDocumento);
				jTextFieldDocumento.setBounds(161, 98, 149, 23);
			}
			{
				jLabelNombres = new JLabel();
				getContentPane().add(jLabelNombres);
				jLabelNombres.setText("nombre(s):");
				jLabelNombres.setBounds(31, 140, 100, 23);
				jLabelNombres.setVisible(false);
			}
			{
				jTextFieldnombres = new JTextField();
				getContentPane().add(jTextFieldnombres);
				jTextFieldnombres.setBounds(161, 140, 149, 23);
				jTextFieldnombres.setVisible(false);
			}
			{
				jLabelApellidos = new JLabel();
				getContentPane().add(jLabelApellidos);
				jLabelApellidos.setText("Apellidos");
				jLabelApellidos.setBounds(31, 175, 100, 16);
				jLabelApellidos.setVisible(false);
			}
			{
				jTextFieldApellidos = new JTextField();
				getContentPane().add(jTextFieldApellidos);
				jTextFieldApellidos.setBounds(161, 172, 149, 23);
				jTextFieldApellidos.setVisible(false);
			}
			{
				jLabelGrado = new JLabel();
				getContentPane().add(jLabelGrado);
				jLabelGrado.setText("Grado");
				jLabelGrado.setBounds(31, 209, 79, 16);
				jLabelGrado.setVisible(false);
			}
			{
				jTextFieldGrado = new JTextField();
				getContentPane().add(jTextFieldGrado);
				jTextFieldGrado.setBounds(161, 209, 149, 23);
				jTextFieldGrado.setVisible(false);
			}
			{
				jLabelSexo = new JLabel();
				getContentPane().add(jLabelSexo);
				jLabelSexo.setText("Sexo");
				jLabelSexo.setBounds(31, 250, 55, 16);
				jLabelSexo.setVisible(false);
			}
			{
				jTextFieldSexo = new JTextField();
				getContentPane().add(jTextFieldSexo);
				jTextFieldSexo.setBounds(161, 244, 149, 23);
				jTextFieldSexo.setVisible(false);
			}
			{
				jLabelTipoPoblacion = new JLabel();
				getContentPane().add(jLabelTipoPoblacion);
				jLabelTipoPoblacion.setText("Tipo Poblacion: ");
				jLabelTipoPoblacion.setBounds(31, 287, 113, 16);
				jLabelTipoPoblacion.setVisible(false);
			}
			{
				jTextFieldTipoPoblacion = new JTextField();
				getContentPane().add(jTextFieldTipoPoblacion);
				jTextFieldTipoPoblacion.setBounds(162, 284, 148, 23);
				jTextFieldTipoPoblacion.setVisible(false);
			}
			{
				jLabelMetodologia = new JLabel();
				getContentPane().add(jLabelMetodologia);
				jLabelMetodologia.setText("Metodologia");
				jLabelMetodologia.setBounds(31, 324, 113, 21);
				jLabelMetodologia.setVisible(false);
			}
			{
				jTextFieldMetodologia = new JTextField();
				getContentPane().add(jTextFieldMetodologia);
				jTextFieldMetodologia.setBounds(162, 323, 148, 23);
				jTextFieldMetodologia.setVisible(false);
			}
			{
				jButtonBuscar = new JButton();
				getContentPane().add(jButtonBuscar);
				jButtonBuscar.setText("BUSCAR");
				jButtonBuscar.setBounds(345, 98, 99, 30);
				jButtonBuscar.addActionListener(this);
			}
			pack();
			this.setSize(465, 175);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int documento = Integer.parseInt(jTextFieldDocumento.getText());
		if (e.getSource() == jButtonBuscar) {
			cn = dataConnection.conexion();
			try {
				pst = cn.prepareStatement(
						"select documento,nombres,apellidos,grado,sexo,tipoPoblacion,modeloPedagogico from estudiante WHERE documento=?");
				pst.setInt(1, documento);
				res = pst.executeQuery();
				if (res.next()) {
					this.setSize(465, 413);
					jLabelApellidos.setVisible(true);
					jTextFieldApellidos.setVisible(true);
					jTextFieldApellidos.setText(res.getString("apellidos"));

					jLabelNombres.setVisible(true);
					jTextFieldnombres.setText(res.getString("nombres"));
					jTextFieldnombres.setVisible(true);

					jLabeldocumento.setVisible(true);
					jTextFieldDocumento.setEditable(false);

					jLabelGrado.setVisible(true);
					jTextFieldGrado.setText(res.getString("grado"));
					jTextFieldGrado.setVisible(true);

					jLabelSexo.setVisible(true);
					jTextFieldSexo.setText(res.getString("sexo"));
					jTextFieldSexo.setVisible(true);

					jLabelTipoPoblacion.setVisible(true);
					jTextFieldTipoPoblacion.setText(res.getString("tipoPoblacion"));
					jTextFieldTipoPoblacion.setVisible(true);

					jLabelMetodologia.setVisible(true);
					jTextFieldMetodologia.setVisible(true);
					jTextFieldMetodologia.setText(res.getString("modeloPedagogico"));
				} else {
					JOptionPane.showMessageDialog(null, "El estudiante buscado no se encuentra en la base de datos");
					jTextFieldDocumento.setText(" ");
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
