package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.WindowConstants;
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
public class principalAdministrador extends javax.swing.JFrame implements
		ActionListener {
	private JButton jButtonBuscarEstudiante;
	private JLabel jLabelDescripcion;
	private JButton jButtonEliminarEstudiante;
	private JButton jButtonSalir;
	private JButton jButtonGenerarInforme;
	private JButton jButtonModificarAdmin;
	private JButton jButtonAgregarEstudiante;
	private JButton jButtonModificarEstudiante;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				principalAdministrador inst = new principalAdministrador();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public principalAdministrador() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jButtonBuscarEstudiante = new JButton();
				getContentPane().add(jButtonBuscarEstudiante);
				jButtonBuscarEstudiante.setText("Buscar Estudiante");
				jButtonBuscarEstudiante.setBounds(23, 61, 172, 49);
				jButtonBuscarEstudiante.addActionListener(this);
			}
			{
				jLabelDescripcion = new JLabel();
				getContentPane().add(jLabelDescripcion);
				jLabelDescripcion.setText("Que deseas hacer...");
				jLabelDescripcion.setBounds(138, 12, 178, 29);
			}
			{
				jButtonModificarEstudiante = new JButton();
				getContentPane().add(jButtonModificarEstudiante);
				jButtonModificarEstudiante.setText("Modificar Estudiante");
				jButtonModificarEstudiante.setBounds(206, 130, 172, 49);
				jButtonModificarEstudiante.addActionListener(this);
			}
			{
				jButtonEliminarEstudiante = new JButton();
				getContentPane().add(jButtonEliminarEstudiante);
				jButtonEliminarEstudiante.setText("Eliminar Estudiante");
				jButtonEliminarEstudiante.setBounds(206, 61, 172, 49);
				jButtonEliminarEstudiante.addActionListener(this);
			}
			{
				jButtonAgregarEstudiante = new JButton();
				getContentPane().add(jButtonAgregarEstudiante);
				jButtonAgregarEstudiante.setText("Agregar Estudiante");
				jButtonAgregarEstudiante.setBounds(23, 130, 172, 49);
				jButtonAgregarEstudiante.addActionListener(this);
			}
			{
				jButtonModificarAdmin = new JButton();
				getContentPane().add(jButtonModificarAdmin);
				jButtonModificarAdmin.setText("Modificar mi Informacion");
				jButtonModificarAdmin.setBounds(23, 196, 246, 48);
				jButtonModificarAdmin.addActionListener(this);
			}
			{
				jButtonSalir = new JButton();
				getContentPane().add(jButtonSalir);
				jButtonSalir.setText("Salir");
				jButtonSalir.setBounds(347, 257, 65, 31);
				jButtonSalir.addActionListener(this);
			}
			{
				jButtonGenerarInforme = new JButton();
				getContentPane().add(jButtonGenerarInforme);
				jButtonGenerarInforme.setText("Generar Informe");
				jButtonGenerarInforme.setBounds(35, 255, 183, 35);
				jButtonGenerarInforme.addActionListener(this);
			}
			pack();
			this.setSize(439, 337);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButtonSalir) {
			principalAplicacion principal = new principalAplicacion();
			principal.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == jButtonAgregarEstudiante) {
			crearEstudiante c = new crearEstudiante();
			c.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == jButtonBuscarEstudiante) {
			buscarEstudiante b = new buscarEstudiante();
			b.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == jButtonEliminarEstudiante) {
			eliminarEstudiante e1 = new eliminarEstudiante();
			e1.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == jButtonModificarAdmin) {
			modificarAdministrador a = new modificarAdministrador();
			a.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == jButtonModificarEstudiante) {
			modificarEstudiante m = new modificarEstudiante();
			m.setVisible(true);
			this.dispose();
		}
		if(e.getSource()==jButtonGenerarInforme){
			generarInforme m=new generarInforme();
			m.setVisible(true);
			this.dispose();
		}

	}

}
