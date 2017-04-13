package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;

import com.toedter.calendar.JDateChooser;

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
public class generarInforme extends javax.swing.JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jButtonGenerarInforme;
	private JLabel jLabelFechaInicio;
	private JLabel jLabelFechaFinal;
	private JLabel jLabeltitulo;
	private JButton jButtonSalir;
	private JLabel jLabelFormatoFecha;
	private JTextField jTextFieldFechaFinal;
	private JTextField jTextFieldFechaInicail;
	private JLabel jLabelDescripcion;
	private JDateChooser jCalendarFechaFinal;
	private JDateChooser jCalendarFechaInicail;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				generarInforme inst = new generarInforme();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public generarInforme() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jButtonGenerarInforme = new JButton();
				getContentPane().add(jButtonGenerarInforme);
				jButtonGenerarInforme.setText("Generar Informe");
				jButtonGenerarInforme.setBounds(146, 188, 100, 23);
				jButtonGenerarInforme.addActionListener(this);
			}
			{
				jLabelFechaInicio = new JLabel();
				getContentPane().add(jLabelFechaInicio);
				jLabelFechaInicio.setText("Fecha Inicial");
				jLabelFechaInicio.setBounds(32, 102, 122, 24);
			}
			{
				jLabelFechaFinal = new JLabel();
				getContentPane().add(jLabelFechaFinal);
				jLabelFechaFinal.setText("Fecha Final");
				jLabelFechaFinal.setBounds(32, 138, 95, 16);
			}
			{
				jLabeltitulo = new JLabel();
				getContentPane().add(jLabeltitulo);
				jLabeltitulo.setText("Generar Informe");
				jLabeltitulo.setBounds(139, 12, 154, 24);
			}
			{
				jLabelDescripcion = new JLabel();
				getContentPane().add(jLabelDescripcion);
				jLabelDescripcion.setText("Coloque las fechas, para generar el informe SEMANAL.");
				jLabelDescripcion.setBounds(32, 41, 340, 39);
			}
			// {
			// jTextFieldFechaInicail = new JTextField();
			// getContentPane().add(jTextFieldFechaInicail);
			// jTextFieldFechaInicail.setBounds(154, 103, 151, 23);
			// }
			// {
			// jTextFieldFechaFinal = new JTextField();
			// getContentPane().add(jTextFieldFechaFinal);
			// jTextFieldFechaFinal.setBounds(154, 138, 151, 23);
			// }
			{
				jCalendarFechaInicail = new JDateChooser();
				getContentPane().add(jCalendarFechaInicail);
				jCalendarFechaInicail.setBounds(154, 103, 151, 23);
			}
			{
				jCalendarFechaFinal = new JDateChooser();
				getContentPane().add(jCalendarFechaFinal);
				jCalendarFechaFinal.setBounds(154, 138, 151, 23);
			}

			{
				jButtonSalir = new JButton();
				getContentPane().add(jButtonSalir);
				jButtonSalir.setText("Salir");
				jButtonSalir.setBounds(329, 204, 36, 23);
				jButtonSalir.addActionListener(this);
			}
			pack();
			this.setSize(400, 276);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jButtonGenerarInforme){
			Date fechaInicial=formatFecha(jCalendarFechaInicail.getDate());
			Date fechaFinal=formatFecha(jCalendarFechaFinal.getDate());
			
		}
		
		if (e.getSource() == jButtonSalir) {
			principalAdministrador p = new principalAdministrador();
			p.setVisible(true);
			this.dispose();
		}
	}

	public Date formatFecha(Date fecha) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format1.format(fecha);
		fecha = java.sql.Date.valueOf(date1);
		return fecha;

	}

}
