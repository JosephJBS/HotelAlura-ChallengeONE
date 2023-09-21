package dbConection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import dao.HuespedDAO;
import models.Huesped;

public class pruebas {

	public static void main(String[] args) throws SQLException {
		ConnectionDB factory = new ConnectionDB();
		Connection con = factory.recuperaConexion();
		HuespedDAO dao = new HuespedDAO(con);

		LocalDate fecha = LocalDate.of(2000, 5, 18);

		dao.modificar("JOSEPH", "BELLIDO", fecha, "PERUANO", "969754397", 4);

		System.out.println(dao.listar());

	}
}
