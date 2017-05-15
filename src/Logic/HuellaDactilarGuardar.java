package Logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.griaule.grfingerjava.FingerprintImage;
import com.griaule.grfingerjava.GrFingerJava;
import com.griaule.grfingerjava.GrFingerJavaException;
import com.griaule.grfingerjava.IFingerEventListener;
import com.griaule.grfingerjava.IImageEventListener;
import com.griaule.grfingerjava.IStatusEventListener;
import com.griaule.grfingerjava.MatchingContext;
import com.griaule.grfingerjava.Template;

import GUI.GuardarHuella;
import GUI.LogInEstudianteHuella;

public class HuellaDactilarGuardar implements IStatusEventListener, IImageEventListener, IFingerEventListener {
	/**
	 * Contexto utilizado para la captura, extracción y coincidencia de huellas
	 * digitales
	 */
	private MatchingContext fingerprintSDK;

	/** Interfaz de usuario donde se muestra la imagena de la huella */
	private GuardarHuella ui;
	/** Indica si la plantilla o template debe ser extraída automáticamente */
	private boolean autoExtract = true;
	/** Arreglo que contiene localmente los datos de las huellas capturadas */
	private ByteArrayInputStream fingerprintData, fingerprintData2;
	/** Arreglo que contiene la longitud del dato de la huella */
	private int fingerprintDataLength, fingerprintDataLenght2;
	/** La imagen de la última huella digital capturada. */
	private FingerprintImage fingerprint, fingerprint2;
	/** La plantilla de la última imagen de huella capturada */
	public com.griaule.grfingerjava.Template template, template2;

	private PreparedStatement pst;
	private PreparedStatement pst1;
	private Connection cn;
	private ResultSet res, res1;

	public HuellaDactilarGuardar() {

	}

	public HuellaDactilarGuardar(GuardarHuella pantallaPrincipal) {
		this.ui = pantallaPrincipal;
	}

	/**
	 * @function: inicializarCaptura
	 * @author: Ricardo Rosero
	 * @access: public
	 * @return:
	 */
	public void inicializarCaptura() {
		try {
			fingerprintSDK = new MatchingContext();
			// Inicializa la captura de huella digital.
			GrFingerJava.initializeCapture(this);
		} catch (Exception e) {
			// Si ocurre un error durante la inicialización se indica con un
			// mensaje y se cierra la aplicación.
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void onFingerDown(String arg0) {

	}

	@Override
	public void onFingerUp(String arg0) {

	}

	/**
	 * @function: onImageAcquired
	 * @author: Ricardo Rosero
	 * @access: public
	 * @return
	 */
	public void onImageAcquired(String idSensor, FingerprintImage huellaDigital) {
		// Almacena la imagen de la huella
		this.fingerprint = huellaDigital;
		// Muestra la imagen obtenida
		ui.showImage(huellaDigital);
		// Muestra la plantilla en la imagen actual
		extract();
	}

	/**
	 * @Metodo: onSensorPlug
	 * @author: Juan Alape
	 * @access: public
	 * @return
	 */
	public void onSensorUnplug(String idSensor) {
		try {
			// comienza la captura con el lector conectado
			GrFingerJava.startCapture(idSensor, this, this);
		} catch (GrFingerJavaException e) {
			// Error con el lector
			e.printStackTrace();
		}
	}

	/**
	 * @Metodo: onSensorPlug
	 * @author: Juan Alape
	 * @access: public
	 * @return
	 */
	public void onSensorPlug(String idSensor) {
		try {
			// Comienza la captura con el lector conectado.
			GrFingerJava.startCapture(idSensor, this, this);
		} catch (GrFingerJavaException e) {
			// Indica si ha ocurrido un error con el lector.
			e.printStackTrace();
		}
	}

	/**
	 * @function: setFingerprintSDKNativeDirectory
	 * @author: Juan Alape
	 * @access: public
	 * @return
	 */
	public static void setFingerprintSDKNativeDirectory(String directorio) {
		File directory = new File(directorio);
		try {
			GrFingerJava.setNativeLibrariesDirectory(directory);
			GrFingerJava.setLicenseDirectory(directory);
		} catch (GrFingerJavaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @function: extrac
	 * @author: Ricardo Rosero
	 * @access: public
	 * @return
	 */
	public void extract() {
		try {
			// Extrae a template de el fingerprint image.
			template = fingerprintSDK.extract(fingerprint);
			// display minutiae/segments/directions into image
			ui.showImage(GrFingerJava.getBiometricImage(template, fingerprint));
		} catch (GrFingerJavaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @function: guardar
	 * @author: Ricardo Rosero
	 * @access: public
	 * @return
	 * @throws: GrFingerJavaException
	 */
	public boolean guardarHuella(int documento, String nombre) throws GrFingerJavaException {
		fingerprintData = new ByteArrayInputStream(template.getData());
		fingerprintDataLength = template.getData().length;
		boolean a = false;
		boolean coinciden = false;
		cn = dataConnection.conexion();
		try {
			pst = cn.prepareStatement("select documento, huella from huella where (documento=?)");
			pst.setInt(1, documento);
			ResultSet rs = pst.executeQuery();
			// ResultSet rs2 = verficaHuellaUsuario.executeQuery();
			// Si se encuentra el nombre en la base de datos
			if (rs.next()) {
				// Lee la plantilla de la base de datos
				byte templateBuffer[] = rs.getBytes(2); // .getBytes(2);
				// Crea una nueva plantilla
				Template referenceTemplate = new Template(templateBuffer);
				// compara las plantilas (actual vs bd)
				fingerprintSDK.setVerificationRotationTolerance(180);
				fingerprintSDK.setVerificationThreshold(10);
				coinciden = fingerprintSDK.verify(template, referenceTemplate);
				if (coinciden) {
					a = false;
				} else {
					pst1 = cn.prepareStatement("update huella set huella=? where documento=?");
					pst1.setBlob(1, fingerprintData, fingerprintDataLength);
					pst1.setInt(2, documento);
					pst1.execute();
					a = true;
				}
			} else if (!rs.next()) {
				PreparedStatement pst2 = cn
						.prepareStatement("INSERT INTO huella(documento, nombres, huella) values(?,?,?)");
				pst2.setInt(1, documento);
				pst2.setString(2, nombre);
				pst2.setBinaryStream(3, fingerprintData, fingerprintDataLength);
				pst2.execute();
				a = true;
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			System.out.println("Se produjo el siguiente error: " + sqlEx.getMessage());
		}
		return a;
	}

}
