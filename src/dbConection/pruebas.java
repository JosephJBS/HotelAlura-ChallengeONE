package dbConection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import dao.HuespedDAO;
import dao.ReservaDAO;
import models.FormaPago;
import models.Huesped;
import models.Reserva;

public class pruebas {

	public static void main(String[] args) throws SQLException {
		ConnectionDB factory = new ConnectionDB();
		Connection con = factory.recuperaConexion();
		ReservaDAO reserva = new ReservaDAO (con);

		LocalDate fecha = LocalDate.of(2000, 5, 18);
		
		Reserva uwu = new Reserva(4,fecha, fecha, 145.20, FormaPago.TRANSFERENCIA);
		
		reserva.modificar(4,fecha, fecha, 200.50, FormaPago.EFECTIVO,1);

		
		System.out.println(reserva.listar());

	}
}
