package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.FormaPago;
import models.Reserva;

public class ReservaDAO {
	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {

		try {
			PreparedStatement statement;
			statement = con.prepareStatement("INSERT INTO RESERVA "
					+ "(IDCLIENTE, FECHAENTRADA,FECHASALIDA,VALOR,FORMAPAGO)" + " VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setInt(1, reserva.getIdCliente());
				statement.setDate(2, Date.valueOf(reserva.getFechaEntrada()));
				statement.setDate(3, Date.valueOf(reserva.getFechaSalida()));
				statement.setDouble(4, reserva.getValor());
				statement.setString(5, reserva.getFormaPago().name());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));

						System.out.println(String.format("Reserva realizada con exito: %s", reserva));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> listar() {
		List<Reserva> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT ID, IDCLIENTE, FECHAENTRADA, " + "FECHASALIDA,VALOR,FORMAPAGO FROM RESERVA");

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Reserva(
								resultSet.getInt("ID"),
								resultSet.getInt("IDCLIENTE"),
								resultSet.getDate("FECHAENTRADA").toLocalDate(),
								resultSet.getDate("FECHASALIDA").toLocalDate(), 
								resultSet.getDouble("VALOR"),
								FormaPago.valueOf(resultSet.getString("FORMAPAGO"))));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public int modificar(Integer idCliente, LocalDate fechaEntrada, LocalDate fechaSalida, double valor,
			FormaPago formapago, Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVA SET " + " IDCLIENTE = ?, "
					+ " FECHAENTRADA = ?," + " FECHASALIDA = ?," + " VALOR = ?," + "FORMAPAGO = ?" + " WHERE ID = ?");

			try (statement) {
				statement.setInt(1, idCliente);
				statement.setDate(2, Date.valueOf(fechaEntrada));
				statement.setDate(3, Date.valueOf(fechaSalida));
				statement.setDouble(4, valor);
				statement.setString(5, formapago.name());
				statement.setInt(6, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVA WHERE ID = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
