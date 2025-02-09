package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import models.Huesped;

public class HuespedDAO {

	private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {

		try {
			PreparedStatement statement;
			statement = con.prepareStatement(
					"INSERT INTO HUESPED " + "(nombre, apellido,fechaNacimiento,nacionalidad,telefono,docIdentidad)"
							+ " VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, Date.valueOf(huesped.getFechaNacimiento()));
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setString(6, huesped.getDocIdentidad());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));

						System.out.println(String.format("Fue insertado el huesped: %s", huesped));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHANACIMIENTO, "
					+ "NACIONALIDAD, TELEFONO , DOCIDENTIDAD FROM HUESPED");

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO").toLocalDate(),
								resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"),
								resultSet.getString("DOCIDENTIDAD")));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public int modificar(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, Integer id, String docIdentidad) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE HUESPED SET " + " NOMBRE = ?, " + " APELLIDO = ?," + " FECHANACIMIENTO = ?,"
							+ " NACIONALIDAD = ?," + " TELEFONO = ?," + " DOCIDENTIDAD = ?" + " WHERE ID = ?");

			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, Date.valueOf(fechaNacimiento));
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setString(6, docIdentidad);
				statement.setInt(7, id);
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
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPED WHERE ID = ?");

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

	public boolean validarExitenciaHuesped(String nacionalidad, String nroDocumento) {
		int resultado = 0;
		boolean existe = false;

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT COUNT(*) FROM HUESPED WHERE NACIONALIDAD = ?  AND" + " DOCIDENTIDAD = ?");

			try (statement) {
				statement.setString(1, nacionalidad);
				statement.setString(2, nroDocumento);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();
				if (resultSet.next()) {
					resultado = resultSet.getInt(1); // Obtiene el valor del primer (y único) resultado
				}

				if (resultado > 0) {
					existe = true;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return existe;
	}
	
	public boolean validarExitenciaHuespedPorId(int id) {
		int resultado = 0;
		boolean existe = false;

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT COUNT(*) FROM HUESPED WHERE id = ? ");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();
				if (resultSet.next()) {
					resultado = resultSet.getInt(1); // Obtiene el valor del primer (y único) resultado
				}

				if (resultado == 1) {
					existe = true;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return existe;
	}

	public List<Huesped> busquedaPorDocumento(String nroDocumento) {
		List<Huesped> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO,"
					+ "NACIONALIDAD, TELEFONO , FECHANACIMIENTO,  DOCIDENTIDAD FROM HUESPED WHERE DOCIDENTIDAD = ? ");

			try (statement) {
				statement.setString(1, nroDocumento);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						resultado.add(new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("FECHANACIMIENTO").toLocalDate(),
								resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"),
								resultSet.getString("DOCIDENTIDAD")));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}
}
