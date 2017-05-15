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

public class HuellaDactilarLogin implements IStatusEventListener, IImageEventListener, IFingerEventListener {
	/**
	 * Contexto utilizado para la captura, extracción y coincidencia de huellas
	 * digitales
	 */
	private MatchingContext fingerprintSDK;

	/** Interfaz de usuario donde se muestra la imagena de la huella */
	private LogInEstudianteHuella ui;
	private LogInEstudianteHuella ui1;
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

	public HuellaDactilarLogin() {

	}

	public HuellaDactilarLogin(LogInEstudianteHuella pantallaPrincipal) {
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
	 * @Metodo: IdentificarPersona
	 * @author: Juan Alape
	 * @access: public
	 * @throws: GrFingerJavaException
	 */
	public boolean identificarPersona() {
		boolean coinciden = false;
		// int cont = 0;
		cn = dataConnection.conexion();
		try {
			// consulta, que trae todas las huellas de la tabla huella
			PreparedStatement pst5 = cn.prepareStatement("select nombres,huella from huella");
			// almacena el resultado de la consulta en "res"
			res = pst5.executeQuery();

			while (res.next()) {
				// cont++;
				// se crea un arreglo de byte con la huella1 tabla
				Blob blob = res.getBlob(2);
				int tamaño = (int) blob.length();
				byte template1[] = blob.getBytes(1, tamaño);
				// byte template1[] = res.getBytes(2);

				// se crean template de referencia para mas adelante comparar
				Template templateReferencia = new Template(template1);

				// si los template no estan vacios
				if (templateReferencia != null) {

					// se realiza las comparaciones entre la imagen tomada en la
					// GUI y lo de la base de datos
					fingerprintSDK.setVerificationRotationTolerance(180);
					fingerprintSDK.setVerificationThreshold(10);
					coinciden = fingerprintSDK.verify(templateReferencia, template);

					// si alguna de las dos huella son verdaderas permiten el
					// acceso
					if (coinciden) {

						JOptionPane.showMessageDialog(null, "bienvenido" + " " + res.getString("nombres"));
						break;
					} else {
						continue;
					}
				}

			} // de lo contrario rechaza la persona
				// JOptionPane.showMessageDialog(null, "La huella ingresada no
				// esta
				// almacenada");

		} catch (SQLException e) {
			System.out.println("Se produjo el siguiente error: " + e.getMessage());
			e.printStackTrace();
		} catch (GrFingerJavaException gr) {
			System.out.println("Error con el scanner: " + gr.getMessage());
			gr.printStackTrace();
		}

		return coinciden;
	}

}
