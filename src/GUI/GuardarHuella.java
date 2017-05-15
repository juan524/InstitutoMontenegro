package GUI;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.JButton;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.griaule.grfingerjava.GrFingerJavaException;

import Logic.HuellaDactilarGuardar;



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
public class GuardarHuella extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @atributos
	 * @author: Ricardo Rosero
	 * @access: private
	 */
	// objecto usado para realizar todas las operaciones relacionadas al
	// Fingerprint-SDK
	private HuellaDactilarGuardar procedimientosSDK;
	// Panel para mostrar la huella digital
	private JPanel fingerprintViewPanel = null;
	// Imagen de la huella actual
	private BufferedImage fingerprintImage = null;

	/**
	 * @function: PantallaPrincipal
	 * @author: Ricardo Rosero
	 * @access: public
	 * @return
	 */
	public GuardarHuella() {
		inicializar();
		initComponents();
		procedimientosSDK.inicializarCaptura();
		// procedimientosSDK.darValores();
		setLocationRelativeTo(null);
		GroupLayout layout = new GroupLayout((JComponent)getContentPane());
		getContentPane().setLayout(layout);
		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(PanelContenedor, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
			.addGap(30)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(jLabelNombres, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
			    .addComponent(jTextFieldNOmbres, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(31)
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(jLabelDocumento, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
			    .addComponent(jTextFieldDoc, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(41, Short.MAX_VALUE));
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addContainerGap(20, 20)
			.addGroup(layout.createParallelGroup()
			    .addGroup(layout.createSequentialGroup()
			        .addGap(0, 0, Short.MAX_VALUE)
			        .addComponent(PanelContenedor, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))
			    .addGroup(layout.createSequentialGroup()
			        .addGap(23)
			        .addGroup(layout.createParallelGroup()
			            .addGroup(layout.createSequentialGroup()
			                .addGroup(layout.createParallelGroup()
			                    .addComponent(jLabelNombres, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
			                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
			                        .addComponent(jLabelDocumento, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
			                        .addGap(19)))
			                .addGap(23)
			                .addGroup(layout.createParallelGroup()
			                    .addComponent(jTextFieldNOmbres, GroupLayout.Alignment.LEADING, 0, 139, Short.MAX_VALUE)
			                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
			                        .addGap(0, 0, Short.MAX_VALUE)
			                        .addComponent(jTextFieldDoc, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
			                        .addGap(6))))
			            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
			                .addGap(0, 71, Short.MAX_VALUE)
			                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
			                .addGap(98)))
			        .addGap(48))));
		setVisible(true);
	}

	/**
	 * @function: inicializar
	 * @author: Ricardo Rosero
	 * @access: public
	 * @return
	 */
	public void inicializar() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("No se pudo aplicar el estilo visual");
		}
		// Crea una instancia de Util
		this.procedimientosSDK = new HuellaDactilarGuardar(this);
	}

	/**
	 * @function: crearPanelHuella
	 * @author: Ricardo Rosero
	 * @access: private
	 * @return
	 */
	private JComponent crearPanelHuella() {
		// Crea un panel nuevo para mostrar la huella
		fingerprintViewPanel = new JPanel() {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			// Se sobreescribe el método paintComponent para habilitar la
			// muestra de la imagen de la huella
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Si hay una imagen para ser mostrada
				if (fingerprintImage != null) {
					// Calcula el tamaño y posición de la imagen para ser
					// pintada el tamaño es ajustado para que ocupe todo el
					// tamaño del panel
					Insets insets = getInsets();
					int transX = insets.left;
					int transY = insets.top;
					int width = getWidth() - getInsets().right - getInsets().left;
					int height = getHeight() - getInsets().bottom - getInsets().top;
					// Se dibuja la imagen
					g.drawImage(fingerprintImage, transX, transY, width, height, null);
				}
			}
		};
		// Se agrega un border alrededor del panel
		fingerprintViewPanel.setBorder((Border) new CompoundBorder(new EmptyBorder(2, 2, 2, 2),
				(Border) new BevelBorder(BevelBorder.LOWERED)));
		fingerprintViewPanel.setToolTipText("Imagen de la última huella capturada.");
		if (fingerprintViewPanel == null) {
			System.exit(1);
			return null;
		} else {
			return fingerprintViewPanel;
		}
	}

	/**
	 * @function: showImage
	 * @author: Ricardo Rosero
	 * @access: public
	 * @return
	 */
	public void showImage(BufferedImage image) {
		// Utiliza el imageProducer para crear una imagen de la huella digital
		fingerprintImage = image;
		// Se dibuja la nueva imagen
		repaint();
	}

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		String grFingerNativeDirectory = new File(".").getAbsolutePath();
		HuellaDactilarGuardar.setFingerprintSDKNativeDirectory(grFingerNativeDirectory);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GuardarHuella inst = new GuardarHuella();

				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	/**
	 * @function: initComponents
	 * @author: Ricardo Rosero
	 * @access: private
	 * @return
	 */

	// <editor-fold defaultstate=”collapsed” desc=”Generated Code”>
	private void initComponents() {
		PanelContenedor = new javax.swing.JPanel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowActivated(java.awt.event.WindowEvent evt) {
				formWindowActivated(evt);
			}

			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});
		PanelContenedor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Huella Digital",
				javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
		PanelContenedor.setLayout(new java.awt.BorderLayout());
		PanelContenedor.getAccessibleContext().setAccessibleParent(fingerprintViewPanel);
		{
			jLabelNombres = new JLabel();
			jLabelNombres.setText("Nombres Y apellidos");
		}
		{
			jLabelDocumento = new JLabel();
			jLabelDocumento.setText("Documento");
		}
		{
			jButton2 = new JButton();
			jButton2.setText("Guardar");
			jButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						btnGuardarActionPerformed(evt);
					} catch (NumberFormatException | GrFingerJavaException e) {

						e.printStackTrace();
					}
				}
			});
		}
		{
			jTextFieldNOmbres = new JTextField();
		}
		{
			jTextFieldDoc = new JTextField();
		}
		pack();
		this.setSize(390, 573);
	}// </editor-fold>

	/**
	 * @function: btnGuardarActionPerformed
	 * @author: Ricardo Rosero
	 * @access: private
	 * @return
	 * @throws GrFingerJavaException
	 * @throws NumberFormatException
	 */
	private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt)
			throws NumberFormatException, GrFingerJavaException {
		boolean respuesta = procedimientosSDK.guardarHuella(Integer.parseInt(jTextFieldDoc.getText()),
				jTextFieldNOmbres.getText());
		if (respuesta == true) {
			JOptionPane.showMessageDialog(null, "guardado");
		} else {
			JOptionPane.showMessageDialog(null, "Huella ya existe");
		}

	}

	/**
	 * @function: formWindowActivated
	 * @author: Ricardo Rosero
	 * @access: private
	 * @return
	 */
	private void formWindowActivated(java.awt.event.WindowEvent evt) {
		this.PanelContenedor.add(this.crearPanelHuella());
	}

	/**
	 * @function: formWindowClosing
	 * @author: Ricardo Rosero
	 * @access: private
	 * @return
	 */
	private void formWindowClosing(java.awt.event.WindowEvent evt) {
	}

	/**
	 * @atributos
	 * @access: private
	 */
	// Variables declaration – do not modify
	private javax.swing.JPanel PanelContenedor;
	private JButton jButton2;
	private JLabel jLabelDocumento;
	private JLabel jLabelNombres;
	private JTextField jTextFieldDoc;
	private JTextField jTextFieldNOmbres;
	// End of variables declaration

}
