package Logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.xml.transform.Result;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ConnectionPropertiesTransform;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class institutoMontenegro {

	private List<Estudiante> estudiantes;
	private Administrador administrador;
	private dataConnection dataconnection;

	public institutoMontenegro(List<Estudiante> estudiantes, Administrador administrador,
			dataConnection dataconnection) {
		super();
		this.estudiantes = estudiantes;
		this.administrador = administrador;
		this.dataconnection = dataconnection;
	}

	public institutoMontenegro() {
		super();
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public dataConnection getDataconnection() {
		return dataconnection;
	}

	public void setDataconnection(dataConnection dataconnection) {
		this.dataconnection = dataconnection;
	}

}
