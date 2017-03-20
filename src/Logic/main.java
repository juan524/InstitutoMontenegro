package Logic;

import java.sql.ResultSet;
import java.text.ParseException;

import GUI.*;

public class main {

	/**
	 * método que 
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		dataConnection connection= new dataConnection();
		institutoMontenegro instituto= new institutoMontenegro();
		ResultSet resultado;
		String nombre;
//		instituto.fechaHoy();
		instituto.actualizarUltimoIngreso(instituto.fechaHoy());
		


	}

}
