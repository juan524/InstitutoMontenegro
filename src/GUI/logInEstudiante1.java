package GUI;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
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
public class logInEstudiante1 extends javax.swing.JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabelTitulo;
	private JButton jButtonHuella;
	private JButton jButtonSalir;

	Connection cn;
	PreparedStatement pst;
	private JButton jButtonDocumento;
	ResultSet rst;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				logInEstudiante1 inst = new logInEstudiante1();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public logInEstudiante1() {
		super();
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagen/Escudo.png"));
		setIconImage(icon);
		setVisible(true);
		initGUI();
	}

	private void initGUI() {
		try {
			setTitle("PAE Instituto Montenegro-Ingreso Estudiante");
			setLocation(400, 250);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jLabelTitulo = new JLabel();
				getContentPane().add(jLabelTitulo);
				jLabelTitulo.setText("Como deseas Ingresar?");
				jLabelTitulo.setBounds(125, 36, 242, 49);
			}
			{
				jButtonHuella = new JButton();
				getContentPane().add(jButtonHuella);
				jButtonHuella.setText("Huella");
				jButtonHuella.setBounds(12, 120, 163, 37);
				jButtonHuella.addActionListener(this);
			}
			{
				jButtonSalir = new JButton();
				getContentPane().add(jButtonSalir);
				jButtonSalir.setIcon(new ImageIcon(LogInAdmin.class.getResource("/imagen/atras.png")));
				jButtonSalir.setBorderPainted(false);
				jButtonSalir.setBounds(337, 210, 40, 40);
				jButtonSalir.addActionListener(this);
			}
			{
				jButtonDocumento = new JButton();
				getContentPane().add(jButtonDocumento);
				jButtonDocumento.setText("Documento");
				jButtonDocumento.setBounds(204, 122, 163, 35);
				jButtonDocumento.addActionListener(this);
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jButtonSalir) {
			principalAplicacion p = new principalAplicacion();
			p.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == jButtonDocumento) {
			logInEstudianteDocumento p = new logInEstudianteDocumento();
			p.setVisible(true);
			this.dispose();
		}
		if (e.getSource() == jButtonHuella) {
			LogInEstudianteHuella p = new LogInEstudianteHuella();
			p.setVisible(true);
			this.dispose();
		}

	}

}
